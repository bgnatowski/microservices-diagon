package pl.bgnat.product.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDtoResponse(
		String id,
		String name,
		String description,
		BigDecimal price
) {
}
