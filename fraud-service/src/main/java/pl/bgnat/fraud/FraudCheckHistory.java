package pl.bgnat.fraud;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "FraudCheckHistory")
@Table(name = "fraud_check_history")
public class FraudCheckHistory {
	@Id
	@SequenceGenerator(
			name = "fraud_id_sequence",
			sequenceName = "fraud_id_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "fraud_id_sequence")
	private Long id;
	private Long customerId;
	private boolean isFraudster;
	private LocalDateTime createdAt;
}
