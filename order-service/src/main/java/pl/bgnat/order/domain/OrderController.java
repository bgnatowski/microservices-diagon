package pl.bgnat.order.domain;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.order.dto.OrderRequest;
import pl.bgnat.order.exception.order.OutOfStockException;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
	@Value("${server.port}")
	private int port;
	private final OrderService orderService;

	// http://localhost:8083/api/v1/inventory
	// http://localhost:8181/api/v1/inventory
	@PostMapping
	@ResponseStatus(CREATED)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod") //name is the same key as in properties
	@Retry(name = "inventory")
	@TimeLimiter(name = "inventory")
	public CompletableFuture<ResponseEntity<String>> placeOrder(@RequestBody OrderRequest orderRequest) {
		log.info("Placing Order");
		return CompletableFuture.supplyAsync(() -> {
			String orderStatus = orderService.placeOrder(orderRequest);
			return ResponseEntity
					.status(CREATED)
					.body(orderStatus);
		});
	}

	// http://localhost:8082/api/v1/order/status
	// http://localhost:8181/api/v1/order/status
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<String> checkStatus(){
		return ResponseEntity.ok(String.format("Order-service is working on port: %d!", port));
	}
	public CompletableFuture<ResponseEntity<String>> fallbackMethod(OrderRequest orderRequest, OutOfStockException exception) {
		log.info("Cannot Place Order Executing Fallback logic");
		return CompletableFuture.supplyAsync(() ->
				ResponseEntity
						.status(SERVICE_UNAVAILABLE)
						.body("Oops! Something went wrong, please order some time!")
		);
	}
}
