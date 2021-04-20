package com.jovanny.egen.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jovanny.egen.Models.Order;
import com.jovanny.egen.Models.Status;

@Repository
@Transactional
public interface StatusRepo extends JpaRepository<Status, Long> {
	
	@Query(value="SELECT * FROM status s where s.status_name = :status_name", nativeQuery = true)
	Optional<Status> getByName(@Param("status_name") String status_name);
}
