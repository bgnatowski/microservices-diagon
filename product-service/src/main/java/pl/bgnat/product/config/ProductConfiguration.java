package pl.bgnat.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bgnat.product.domain.ProductDtoMapper;

@Configuration
public class ProductConfiguration {
	@Bean
	public ProductDtoMapper productDtoMapper(){
		return new ProductDtoMapper();
	}
}
