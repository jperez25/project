package com.jovanny.egen.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class Order_Details {
	
	public Order_Details() {	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int order_details_id;
	
	//An order can only have one order
	public int item_id;
	
	//An order can have multiple items
	public int order_id;
	
	public int quantity;
	public Double subtotal;
	public int tax;
	
	public int getOrder_details_id() {
		return order_details_id;
	}
	public void setOrder_details_id(int order_details_id) {
		this.order_details_id = order_details_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}


}
