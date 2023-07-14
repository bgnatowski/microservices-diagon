package pl.bgnat.inventory.domain;

import pl.bgnat.inventory.dto.InventoryDto;

import java.util.function.Function;

public class InventoryDtoMapper implements Function<Inventory, InventoryDto> {
	@Override
	public InventoryDto apply(Inventory inventory) {
		return InventoryDto.builder()
				.skuCode(inventory.getSkuCode())
				.isInStock(inventory.getQuantity()>0)
				.build();
	}
}
