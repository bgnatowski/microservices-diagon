package pl.bgnat.customer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bgnat.customer.dto.CustomerRegistrationRequest;
import pl.bgnat.customer.dto.FraudCheckResponse;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final RestTemplate restTemplate;
	@Value("${microservice.fraud.url}")
	private String fraudURL;
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
				fraudURL,
				FraudCheckResponse.class,
				customer.getId()
		);
		if(fraudCheckResponse.isFraudster())
			throw new IllegalStateException("fraudster"); //todo custom exception
		// todo send notification
	}
}
