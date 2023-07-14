package pl.bgnat.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bgnat.order.domain.OrderLineItemsMapper;

@Configuration
public class OrderConfig {
	@Bean
	public OrderLineItemsMapper orderLineItemsMapper(){
		return new OrderLineItemsMapper();
	}

}
