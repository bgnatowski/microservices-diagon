package pl.bgnat.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderLineItemsMapper orderLineItemsMapper;
	private final WebClient webClient;

	@Value("${microservice.inventory.url.get}")
	private String inventoryGetUrl;
	public void placeOrder(OrderRequest orderRequest) {
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

		// todo Call Inventory Service and place order if product is in stock
		InventoryDto[] inventoryResponseArray = webClient.get()
				.uri(inventoryGetUrl,
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryDto[].class)
				.block();

		boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
				.allMatch(InventoryDto::isInStock);
		String productsScuCodeNotInStock = Arrays.stream(inventoryResponseArray)
				.filter(inventoryDto -> !inventoryDto.isInStock())
				.map(InventoryDto::skuCode)
				.collect(Collectors.joining(", "));
		if(!allProductsInStock)
			throw new OutOfStockException("Some products are out of stock! SkuCodes out of stock: " + productsScuCodeNotInStock);
		orderRepository.save(order);
	}
}
