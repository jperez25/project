package com.jovanny.egen.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.jovanny.egen.Models.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
	
	@Query(value="SELECT * FROM orders o where o.customer_id = :customer_id", nativeQuery = true)
	Optional<Order> getByCustomerID(@Param("customer_id") Long customer_id);
	
	
	@Query(value="SELECT * FROM orders o where o.id = :order_id", nativeQuery = true)
	Optional<Order> getByOrderID(@Param("order_id") Long order_id);
		
	/*
	@Modifying
	@Query(value="UPDATE `egenorderprocessing`.`orders` "
			+ "SET "
			+ "`customer_id` = :newOrder.getCustomer_id() "
			
			+ "`order_status` = :newOrder.getOrder_status() "
					
			+ "`modified_at` = 1"
			, nativeQuery = true)
	Optional<Order> updateOrder(@Param("newOrder") Order newOrder);*/
}
