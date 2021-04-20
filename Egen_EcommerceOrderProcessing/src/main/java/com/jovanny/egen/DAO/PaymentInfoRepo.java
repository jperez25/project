package com.jovanny.egen.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jovanny.egen.Models.Order_Details;
import com.jovanny.egen.Models.Payment_info;

public interface PaymentInfoRepo extends JpaRepository<Payment_info, Integer> { 
	
	@Query(value="SELECT * FROM payment p where p.order_id = :order_id", nativeQuery = true)
	List<Payment_info> getPaymentsByOrderID(@Param("order_id") int order_id);
	
	@Query(value="SELECT * FROM payment p where p.order_id = :order_id and p.payment_id = :payment_id", nativeQuery = true)
	List<Order_Details> getPaymentsByOrderIdandItemId(@Param("order_id") long order_id, @Param("payment_id") long payment_id);
}
