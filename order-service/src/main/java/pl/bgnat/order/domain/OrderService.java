package pl.bgnat.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bgnat.order.dto.OrderRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderLineItemsMapper orderLineItemsMapper;
	public void placeOrder(OrderRequest orderRequest){
		List<OrderLineItems> orderLineItems = orderRequest.orderLineItemsDtoList()
				.stream()
				.map(orderLineItemsMapper)
				.collect(Collectors.toList());

		Order order = Order.builder()
				.orderNumber(UUID.randomUUID().toString())
				.orderLineItemsList(orderLineItems)
				.build();

		orderRepository.save(order);
	}
}
