package pl.bgnat.inventory.dto;

import lombok.Builder;

@Builder
public record InventoryResponseDto(
		String skuCode,
		boolean isInStock
) {
}
