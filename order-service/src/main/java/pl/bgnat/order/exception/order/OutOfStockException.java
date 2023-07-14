package pl.bgnat.order.exception.order;

import pl.bgnat.order.exception.ResourceNotFoundException;

public class OutOfStockException extends ResourceNotFoundException {
	public OutOfStockException(String message) {
		super(message);
	}
}
