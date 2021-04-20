package com.jovanny.egen.BatchObjects;

import java.util.ArrayList;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.jovanny.egen.Models.Order_Wrapper;

public class Reader implements ItemReader<Order_Wrapper>  {

	private ArrayList<Order_Wrapper> orders = new ArrayList<Order_Wrapper>();

	private int count = 0;
	
	@Override
	public Order_Wrapper read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		if (count < orders.size()) {
			return orders.get(count++);
		} else {
			count = 0;
		}
		
		return null;
	}
}
