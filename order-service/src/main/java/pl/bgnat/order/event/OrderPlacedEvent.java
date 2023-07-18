package pl.bgnat.order.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class OrderPlacedEvent extends ApplicationEvent {
	private String orderNumber;

	public OrderPlacedEvent(Object source, String orderNumber) {
		super(source);
		this.orderNumber = orderNumber;
	}

	public OrderPlacedEvent(Object source, Clock clock, String orderNumber) {
		super(source, clock);
		this.orderNumber = orderNumber;
	}

	public OrderPlacedEvent(String orderNumber) {
		super(orderNumber);
		this.orderNumber = orderNumber;
	}
}
