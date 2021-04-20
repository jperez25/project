package com.jovanny.egen;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.jovanny.egen.Controllers.MainController;
import com.jovanny.egen.DAO.Egen_DB;
import com.jovanny.egen.Models.Customer;
import com.jovanny.egen.Models.Order;


@SpringBootTest
@AutoConfigureMockMvc
public class WebRestTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private Egen_DB _service;

	@Test
	public void shouldReturnOrder() throws Exception {
		
		this.mockMvc.perform(get("/api/order/getOrder?order_id=1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void greetingShouldReturnAllOrders() throws Exception {
		
		//when(_service.orders.findAll()).thenReturn(new ArrayList<Order>());
		
		this.mockMvc.perform(get("/api/order/getAllOrders")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

}