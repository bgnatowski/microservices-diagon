package pl.bgnat.order.domain;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import pl.bgnat.order.dto.InventoryDto;
import pl.bgnat.order.dto.OrderRequest;
import pl.bgnat.order.exception.order.OutOfStockException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderLineItemsMapper orderLineItemsMapper;
	private final WebClient.Builder webClientBuilder;
	private final ObservationRegistry observationRegistry;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Value("${microservice.inventory.url.get}")
	private String inventoryGetUrl;

	public String placeOrder(OrderRequest orderRequest) {
		List<OrderLineItems> orderLineItems = orderRequest.orderLineItemsDtoList()
				.stream()
				.map(orderLineItemsMapper)
				.collect(Collectors.toList());

		Order order = Order.builder()
				.orderNumber(UUID.randomUUID().toString())
				.orderLineItemsList(orderLineItems)
				.build();

		List<String> skuCodes = order.getOrderLineItemsList().stream()
				.map(OrderLineItems::getSkuCode)
				.collect(Collectors.toList());

		log.info("Calling inventory service");

		Observation inventoryServiceObservation = Observation.createNotStarted(
				"inventory-service-lookup",
				this.observationRegistry);
		inventoryServiceObservation.lowCardinalityKeyValue("call", "inventory-service");

		// Call Inventory Service and place order if product is in stock
		return inventoryServiceObservation.observe(() -> {
			InventoryDto[] inventoryResponseArray = callInventoryAndGetDtosBySkuCode(skuCodes);

			String productsScuCodeNotInStock = getProductsScuCodeNotInStock(inventoryResponseArray);

			if (!isAllProductsInStock(inventoryResponseArray))
				throw new OutOfStockException("Some products are out of stock! SkuCodes out of stock: " + productsScuCodeNotInStock);
			orderRepository.save(order);
			return "Order Placed Successfully";
		});
	}

	private static String getProductsScuCodeNotInStock(InventoryDto[] inventoryResponseArray) {
		return Arrays.stream(inventoryResponseArray)
				.filter(inventoryDto -> !inventoryDto.isInStock())
				.map(InventoryDto::skuCode)
				.collect(Collectors.joining(", "));
	}

	private static boolean isAllProductsInStock(InventoryDto[] inventoryResponseArray) {
		return Arrays.stream(inventoryResponseArray)
				.allMatch(InventoryDto::isInStock);
	}

	private InventoryDto[] callInventoryAndGetDtosBySkuCode(List<String> skuCodes) {
		InventoryDto[] inventoryResponseArray = webClientBuilder.build().get()
				.uri("http://inventory-service/api/v1/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryDto[].class)
				.block();
		return inventoryResponseArray;
	}
}
