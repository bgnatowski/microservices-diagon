package pl.bgnat.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bgnat.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
