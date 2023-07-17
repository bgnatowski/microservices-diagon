package pl.bgnat.order.domain;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.order.dto.OrderRequest;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
class OrderController {
	private final OrderService orderService;

	@PostMapping("/add")
	@ResponseStatus(CREATED)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod") //name is the same key as in properties
	@TimeLimiter(name = "inventory")
	public CompletableFuture<ResponseEntity<String>> placeOrder(@RequestBody OrderRequest orderRequest) {
		return CompletableFuture.supplyAsync(() -> {
					String orderStatus = orderService.placeOrder(orderRequest);
					return ResponseEntity
							.status(CREATED)
							.body(orderStatus);
				});
	}

	public CompletableFuture<ResponseEntity<String>> fallbackMethod(OrderRequest orderRequest, Throwable throwable) {
		return CompletableFuture.supplyAsync(() ->
				ResponseEntity
						.status(SERVICE_UNAVAILABLE)
						.body("Oops! Something went wrong, please order some time!")
		);
	}
}
