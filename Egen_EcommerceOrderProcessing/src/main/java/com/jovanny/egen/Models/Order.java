package com.jovanny.egen.Models;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	public Order() {}
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private int customer_id;
	private String order_shipping_address;
	private String order_shipping_city;
	private String order_shipping_state;
	private String order_shipping_zip;
	private String order_status;
	private String delivery_method;
	private String created_at;
	private String modified_at;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getOrder_shipping_address() {
		return order_shipping_address;
	}
	public void setOrder_shipping_address(String order_shipping_address) {
		this.order_shipping_address = order_shipping_address;
	}
	public String getOrder_shipping_city() {
		return order_shipping_city;
	}
	public void setOrder_shipping_city(String order_shipping_city) {
		this.order_shipping_city = order_shipping_city;
	}
	public String getOrder_shipping_state() {
		return order_shipping_state;
	}
	public void setOrder_shipping_state(String order_shipping_state) {
		this.order_shipping_state = order_shipping_state;
	}
	public String getOrder_shipping_zip() {
		return order_shipping_zip;
	}
	public void setOrder_shipping_zip(String order_shipping_zip) {
		this.order_shipping_zip = order_shipping_zip;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getDelivery_method() {
		return delivery_method;
	}
	public void setDelivery_method(String delivery_method) {
		this.delivery_method = delivery_method;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getModified_at() {
		return modified_at;
	}
	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}
	

}
