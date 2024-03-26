package pl.bgnat.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
public record OrderLineItemsDto(
		Long id,
		String skuCode,
		BigDecimal price,
		Integer quantity
) {
}
