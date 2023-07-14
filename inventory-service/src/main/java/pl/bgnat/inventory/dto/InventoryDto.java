package pl.bgnat.inventory.dto;

import lombok.Builder;

@Builder
public record InventoryDto(
		String skuCode,
		boolean isInStock
) {
}
