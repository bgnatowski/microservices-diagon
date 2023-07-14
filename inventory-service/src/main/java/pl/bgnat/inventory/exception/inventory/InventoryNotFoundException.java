package pl.bgnat.inventory.exception.inventory;

import pl.bgnat.inventory.exception.ResourceNotFoundException;

public class InventoryNotFoundException extends ResourceNotFoundException {
	public InventoryNotFoundException(String message) {
		super(message);
	}
}
