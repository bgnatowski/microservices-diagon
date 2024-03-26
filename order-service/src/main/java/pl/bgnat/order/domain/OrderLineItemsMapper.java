package pl.bgnat.order.domain;

import pl.bgnat.order.dto.OrderLineItemsDto;

import java.util.function.Function;

public class OrderLineItemsMapper implements Function<OrderLineItemsDto, OrderLineItems> {
	@Override
	public OrderLineItems apply(OrderLineItemsDto orderLineItemsDto) {
		return OrderLineItems.builder()
				.price(orderLineItemsDto.price())
				.quantity(orderLineItemsDto.quantity())
				.skuCode(orderLineItemsDto.skuCode())
				.build();
	}
}
