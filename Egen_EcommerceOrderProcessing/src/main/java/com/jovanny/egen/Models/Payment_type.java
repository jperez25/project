package com.jovanny.egen.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_type")
public class Payment_type {

	public Payment_type() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String payment_type_id;
}
