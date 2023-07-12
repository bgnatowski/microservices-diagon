package pl.bgnat.customer.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bgnat.customer.dto.CustomerRegistrationRequest;
import pl.bgnat.customer.service.CustomerService;

@Slf4j
@RestController("api/v1/customers")
public record CustomerController(
		CustomerService customerService
) {

	@PostMapping("/add")
	public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
		log.info("new customer registration {}", customerRegistrationRequest);
		customerService.registerCustomer(customerRegistrationRequest);
	}
}
