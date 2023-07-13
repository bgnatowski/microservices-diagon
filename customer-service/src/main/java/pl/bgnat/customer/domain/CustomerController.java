package pl.bgnat.customer.domain;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bgnat.customer.dto.CustomerRegistrationRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
class CustomerController{
	private final CustomerService customerService;

	@PostMapping("/add")
	void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
		log.info("new customer registration {}", customerRegistrationRequest);
		customerService.registerCustomer(customerRegistrationRequest);
	}
}
