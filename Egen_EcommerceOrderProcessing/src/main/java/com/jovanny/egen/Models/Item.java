package com.jovanny.egen.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {

	public Item() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int item_id;
	private Double item_price;
	
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public Double getItem_price() {
		return item_price;
	}
	public void setItem_price(Double item_price) {
		this.item_price = item_price;
	}
	
}
