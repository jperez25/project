package com.jovanny.egen.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jovanny.egen.Models.Order_Details;

public interface OrderDetailsRepo extends JpaRepository<Order_Details, Long> { 
	@Query(value="SELECT * FROM order_details op where op.order_id = :order_id", nativeQuery = true)
	List<Order_Details> getByOrderID(@Param("order_id") long order_id);
	
	@Query(value="SELECT * FROM order_details op where op.order_id = :order_id and op.item_id = :item_id", nativeQuery = true)
	List<Order_Details> getItemsByOrderIdandItemId(@Param("order_id") long order_id, @Param("item_id") long item_id);
}