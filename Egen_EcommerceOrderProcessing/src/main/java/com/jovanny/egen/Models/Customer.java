package com.jovanny.egen.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	public Customer() {	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int customer_id;
	
	private String customer_name;
	private String customer_address;
	private String customer_phone_no;
	private String customer_email;
	
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_phone_no() {
		return customer_phone_no;
	}
	public void setCustomer_phone_no(String customer_phone_no) {
		this.customer_phone_no = customer_phone_no;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	
}
