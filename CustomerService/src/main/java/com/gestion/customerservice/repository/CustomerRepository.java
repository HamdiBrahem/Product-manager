package com.gestion.customerservice.repository;
import com.gestion.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can define custom queries here if needed
    Customer findByEmail(String email);
    Optional<Customer> findByUsername(String username);
}
