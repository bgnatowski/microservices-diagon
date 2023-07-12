package pl.bgnat.customer.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bgnat.customer.dao.repository.CustomerRepository;
import pl.bgnat.customer.model.Customer;

@RequiredArgsConstructor
@Service("jpa")
public class CustomerJPADataAccessService implements CustomerDao {
	private final CustomerRepository customerRepository;

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}
}
