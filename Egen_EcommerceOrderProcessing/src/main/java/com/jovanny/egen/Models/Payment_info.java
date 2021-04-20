package com.jovanny.egen.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment_info {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int payment_id;
	private int order_id;
	private String order_payment_method;
	private String payment_amount;
	private String order_payment_date;
	private String order_payment_confirmation_number;
	private String  order_billing_address_1;
	private String order_billing_city;
	private String order_billing_state;
	private int order_billing_zip;
	
	
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_payment_method() {
		return order_payment_method;
	}
	public void setOrder_payment_method(String order_payment_method) {
		this.order_payment_method = order_payment_method;
	}
	public String getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getorder_payment_date() {
		return order_payment_date;
	}
	public void setorder_payment_date(String order_paymnet_date) {
		this.order_payment_date = order_paymnet_date;
	}
	public String getOrder_payment_confirmation_number() {
		return order_payment_confirmation_number;
	}
	public void setOrder_payment_confirmation_number(String order_payment_confirmation_number) {
		this.order_payment_confirmation_number = order_payment_confirmation_number;
	}
	public String getOrder_billing_address_1() {
		return order_billing_address_1;
	}
	public void setOrder_billing_address_1(String order_billing_address_1) {
		this.order_billing_address_1 = order_billing_address_1;
	}
	public String getOrder_billing_city() {
		return order_billing_city;
	}
	public void setOrder_billing_city(String order_billing_city) {
		this.order_billing_city = order_billing_city;
	}
	public String getOrder_billing_state() {
		return order_billing_state;
	}
	public void setOrder_billing_state(String order_billing_state) {
		this.order_billing_state = order_billing_state;
	}
	public int getOrder_billing_zip() {
		return order_billing_zip;
	}
	public void setOrder_billing_zip(int order_billing_zip) {
		this.order_billing_zip = order_billing_zip;
	}
	

}
