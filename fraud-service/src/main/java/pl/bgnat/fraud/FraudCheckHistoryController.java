package pl.bgnat.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.dto.FraudCheckResponse;
import pl.bgnat.fraud.FraudCheckHistoryService;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckHistoryController {
	private final FraudCheckHistoryService fraudCheckHistoryService;
	@GetMapping(path = "{customerId}")
	public FraudCheckResponse isFraudster(
			@PathVariable("customerId") Long customerId){
		boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
		log.info("fraud check request for customer {}", customerId);
		return FraudCheckResponse.builder()
				.isFraudster(isFraudulentCustomer)
				.build();
	}

}
