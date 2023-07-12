package pl.bgnat.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckHistoryService {
	private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
	private final Clock clock;
	public boolean isFraudulentCustomer(Long customerId){
		fraudCheckHistoryRepository.save(
				FraudCheckHistory.builder()
						.isFraudster(false)
						.customerId(customerId)
						.createdAt(LocalDateTime.now(clock))
						.build()
		);
		return false;  //todo
	}


}
