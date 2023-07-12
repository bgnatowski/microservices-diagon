package pl.bgnat.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.bgnat.customer.dao.CustomerDao;
import pl.bgnat.customer.dto.CustomerRegistrationRequest;
import pl.bgnat.customer.model.Customer;

@Service
@RequiredArgsConstructor
public class CustomerService {
	@Qualifier("jpa")
	private final CustomerDao customerDao;

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
		customerDao.save(customer);
	}
}
