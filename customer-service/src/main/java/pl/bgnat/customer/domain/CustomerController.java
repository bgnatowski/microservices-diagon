package pl.bgnat.customer.domain;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.customer.dto.CustomerRegistrationRequest;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
class CustomerController{
	@Value("${server.port}")
	private int port;

	private final CustomerService customerService;

	// http://localhost:8084/api/v1/customer
	// http://localhost:8181/api/v1/customer
	@PostMapping
	void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
		log.info("new customer registration {}", customerRegistrationRequest);
		customerService.registerCustomer(customerRegistrationRequest);
	}

//	http://localhost:8084/api/v1/customer
//	http://localhost:8181/api/v1/customer
	@GetMapping
	ResponseEntity<List<Customer>> getCustomers(){
		log.info("retrieve customers");
		return ResponseEntity.ok(customerService.getCustomers());
	}
//  http://localhost:8084/api/v1/customer/1
//  http://localhost:8181/api/v1/customer/1
	@GetMapping("/{id}")
	ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
		log.info("retrieve customer by {}", id);
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}
//	http://localhost:8084/api/v1/customer/1
//	http://localhost:8181/api/v1/customer/1
	@DeleteMapping("/{id}")
	String deleteCustomerById(@PathVariable Long id){
		log.info("deleting customer by {}", id);
		return customerService.deleteCustomerById(id);
	}
//	http://localhost:8084/api/v1/customer/status
//	http://localhost:8181/api/v1/customer/status
	@GetMapping("/status")
	ResponseEntity<String> checkStatus(){
		return ResponseEntity.ok(String.format("Customer-service is working on port: %d!", port));
	}
}
