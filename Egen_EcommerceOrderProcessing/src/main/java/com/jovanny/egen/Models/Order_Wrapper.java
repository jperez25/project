package com.jovanny.egen.Models;

import java.util.List;

//A order has:
//A customer
//Payment info
//Shipping address
//A list of items
public class Order_Wrapper {
	
	private Order order;
	private Customer customer;
	private List<Payment_info> payment_info;
	private List<Item> items;

	public Order_Wrapper() {
		// TODO Auto-generated constructor stub
	}
	

	public Order_Wrapper(Customer c, List<Payment_info> p_i, List<Item> i) {
		this.customer =c;
		this.payment_info = p_i;
		this.items = i;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<Payment_info> getPayment_info() {
		return payment_info;
	}


	public void setPayment_info(List<Payment_info> payment_info) {
		this.payment_info = payment_info;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
}
