package pl.bgnat.order.config;

import io.micrometer.observation.ObservationRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import pl.bgnat.order.domain.OrderLineItemsMapper;
import pl.bgnat.order.domain.OrderPlacedEventListener;

/**
 * In this class we'll add all the manual configuration required for Observability to
 * work.
 */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class OrderConfig {
	private final KafkaTemplate kafkaTemplate;
	@PostConstruct
	void setup() {
		this.kafkaTemplate.setObservationEnabled(true);
	}
	@Bean
	public OrderLineItemsMapper orderLineItemsMapper(){
		return new OrderLineItemsMapper();
	}

//	@Bean
//	public OrderPlacedEventListener orderPlacedEventListener(){
//		return new OrderPlacedEventListener(kafkaTemplate, ObservationRegistry.NOOP);
//	}

}
