package pl.bgnat.order.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "order_line_item")
public class OrderLineItems {
	@Id
	@SequenceGenerator(
			name = "order_line_item_id_sequence",
			sequenceName = "order_line_item_id_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "order_line_item_id_sequence")
	private Long id;
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
