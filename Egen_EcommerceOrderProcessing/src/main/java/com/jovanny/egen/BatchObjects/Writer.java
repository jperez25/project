package com.jovanny.egen.BatchObjects;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.jovanny.egen.Models.Order_Wrapper;

public class Writer implements ItemWriter<Order_Wrapper> {

	@Override
	public void write(List<? extends Order_Wrapper> orders) throws Exception {
		for (Order_Wrapper order : orders) {
			System.out.println("Writing the data " + order.getCustomer().getCustomer_name());
		}
	}

}
