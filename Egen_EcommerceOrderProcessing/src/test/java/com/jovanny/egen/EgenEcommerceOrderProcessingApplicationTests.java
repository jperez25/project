package com.jovanny.egen;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jovanny.egen.Controllers.MainController;

@SpringBootTest
class EgenEcommerceOrderProcessingApplicationTests {
	
	@Autowired
	private MainController controller;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		
		assertThat(controller).isNotNull();
		System.out.println("Test passed");
	}
	
	@Test
	public void shouldReturnOrder() throws Exception {
		
		this.mockMvc.perform(get("/api/order/getOrder?id=1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

}
