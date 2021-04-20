package com.jovanny.egen.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jovanny.egen.Models.Customer;
import com.jovanny.egen.Models.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {
	
	@Query(value="SELECT * FROM item i where i.item_id = :item_id", nativeQuery = true)
	Optional<Item> getByItemID(@Param("item_id") int item_id);
	
}
