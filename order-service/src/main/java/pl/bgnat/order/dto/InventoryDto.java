package pl.bgnat.order.dto;

import lombok.Builder;

@Builder
public record InventoryDto(
		String skuCode,
		boolean isInStock
) {
}
