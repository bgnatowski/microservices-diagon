package pl.bgnat.product.domain;

import pl.bgnat.product.dto.ProductDtoResponse;

import java.util.function.Function;

public class ProductDtoMapper implements Function<Product, ProductDtoResponse> {
	@Override
	public ProductDtoResponse apply(Product product) {
		return ProductDtoResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
