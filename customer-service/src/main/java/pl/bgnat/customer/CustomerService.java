package pl.bgnat.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bgnat.customer.CustomerRepository;
import pl.bgnat.dto.CustomerRegistrationRequest;
import pl.bgnat.dto.FraudCheckResponse;
import pl.bgnat.customer.Customer;

@Service
@RequiredArgsConstructor
public class CustomerService {
	@Qualifier("jpa")
	private final CustomerRepository customerRepository;
	private final RestTemplate restTemplate;

	public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
		Customer customer = Customer.builder()
				.firstName(customerRegistrationRequest.firstName())
				.lastName(customerRegistrationRequest.lastName())
				.email(customerRegistrationRequest.email())
				.password(customerRegistrationRequest.password())
				.dateOfBirth(customerRegistrationRequest.dateOfBirth())
				.build();

		// todo check if email valid
		// todo check if email not taken
		// todo check if date of birth valid
		customerRepository.saveAndFlush(customer);
		// todo check if fraudster
		FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
				"http://localhost:8081/api/v1/fraud-check/{cusomerId}",
				FraudCheckResponse.class,
				customer.getId()
		);
		if(fraudCheckResponse.isFraudster())
			throw new IllegalStateException("fraudster"); //todo custom exception
		// todo send notification
	}
}
