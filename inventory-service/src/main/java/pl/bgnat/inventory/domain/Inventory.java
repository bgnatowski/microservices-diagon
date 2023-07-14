package pl.bgnat.inventory.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	@SequenceGenerator(
			name = "inventory_id_sequence",
			sequenceName = "inventory_id_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "inventory_id_sequence")
	private Long id;
	private String skuCode;
	private Integer quantity;

}
