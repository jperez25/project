package com.jovanny.egen.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jovanny.egen.Models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	@Query(value="SELECT * FROM customer c where c.customer_id = :customer_id", nativeQuery = true)
	Optional<Customer> getByCustomerID(@Param("customer_id") int customer_id);
}
