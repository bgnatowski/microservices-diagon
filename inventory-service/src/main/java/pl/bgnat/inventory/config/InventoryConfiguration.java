package pl.bgnat.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bgnat.inventory.domain.InventoryDtoMapper;

@Configuration
public class InventoryConfiguration {
	@Bean
	public InventoryDtoMapper inventoryDtoMapper(){
		return new InventoryDtoMapper();
	}
}
