package pl.bgnat.inventory.domain;

import pl.bgnat.inventory.dto.InventoryResponseDto;

import java.util.function.Function;

public class InventoryDtoMapper implements Function<Inventory, InventoryResponseDto> {
	@Override
	public InventoryResponseDto apply(Inventory inventory) {
		return InventoryResponseDto.builder()
				.skuCode(inventory.getSkuCode())
				.isInStock(inventory.getQuantity()>0)
				.build();
	}
}
