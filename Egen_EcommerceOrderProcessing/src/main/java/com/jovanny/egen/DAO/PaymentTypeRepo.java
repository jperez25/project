package com.jovanny.egen.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jovanny.egen.Models.Payment_type;

public interface PaymentTypeRepo extends JpaRepository<Payment_type, Integer> { }
