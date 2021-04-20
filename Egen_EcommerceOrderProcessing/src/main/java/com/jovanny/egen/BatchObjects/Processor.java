package com.jovanny.egen.BatchObjects;

import org.springframework.batch.item.ItemProcessor;

import com.jovanny.egen.Models.Order_Wrapper;

public class Processor implements ItemProcessor<Order_Wrapper, String> {

	@Override
	public String process(Order_Wrapper orders) throws Exception {
		return orders.getCustomer().getCustomer_name();
	}

}