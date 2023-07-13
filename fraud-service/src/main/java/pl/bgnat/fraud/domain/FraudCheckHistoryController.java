package pl.bgnat.fraud.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bgnat.fraud.dto.FraudCheckResponse;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckHistoryController {
	private final FraudCheckHistoryService fraudCheckHistoryService;
	@GetMapping(path = "{customerId}")
	FraudCheckResponse isFraudster(
			@PathVariable("customerId") Long customerId){
		boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
		log.info("fraud check request for customer {}", customerId);
		return FraudCheckResponse.builder()
				.isFraudster(isFraudulentCustomer)
				.build();
	}

}
