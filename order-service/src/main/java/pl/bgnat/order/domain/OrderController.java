package pl.bgnat.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.order.dto.OrderRequest;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
class OrderController {
	private final OrderService orderService;
	@PostMapping("/add")
	@ResponseStatus(CREATED)
	ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
		orderService.placeOrder(orderRequest);
		return new ResponseEntity("Order Placed Successfully", CREATED);
	}
}
