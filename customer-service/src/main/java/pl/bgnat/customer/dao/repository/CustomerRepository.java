package pl.bgnat.customer.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bgnat.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
