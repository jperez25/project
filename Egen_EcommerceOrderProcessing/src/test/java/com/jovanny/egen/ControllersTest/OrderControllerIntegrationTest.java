package com.jovanny.egen.ControllersTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.jovanny.egen.DAO.Egen_DB;


@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class OrderControllerIntegrationTest {
	
	@Autowired
    private MockMvc mvc;

    @Autowired
    private Egen_DB  _service;

}
