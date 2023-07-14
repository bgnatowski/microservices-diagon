package pl.bgnat.order.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(
		List<OrderLineItemsDto> orderLineItemsDtoList
) {
}
