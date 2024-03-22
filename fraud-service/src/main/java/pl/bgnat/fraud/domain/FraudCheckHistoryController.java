package pl.bgnat.fraud.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.fraud.dto.FraudCheckResponse;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud")
@RequiredArgsConstructor
public class FraudCheckHistoryController {
	@Value("${server.port}")
	private int port;
	private final FraudCheckHistoryService fraudCheckHistoryService;
//	http://localhost:8085/api/v1/fraud/1
//	http://localhost:8181/api/v1/fraud/1
	@GetMapping(path = "{customerId}")
	FraudCheckResponse isFraudster(
			@PathVariable("customerId") Long customerId){
		boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
		log.info("fraud check request for customer {}", customerId);
		return FraudCheckResponse.builder()
				.isFraudster(isFraudulentCustomer)
				.build();
	}

//	http://localhost:8085/api/v1/fraud/status
//	http://localhost:8181/api/v1/fraud/status
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<String> checkStatus(){
		return ResponseEntity.ok(String.format("Fraud-service is working on port: %d!", port));
	}
}
