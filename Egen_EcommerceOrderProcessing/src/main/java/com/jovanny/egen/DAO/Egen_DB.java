package com.jovanny.egen.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
public class Egen_DB extends JdbcDaoSupport {
	

	@Autowired
	public OrderRepo orders;
	@Autowired
	public CustomerRepo customers;
	@Autowired
	public ItemRepo items;
	@Autowired
	public OrderDetailsRepo or_details;
	@Autowired
	public PaymentInfoRepo payment_info;
	@Autowired
	public PaymentTypeRepo payment_type;
	@Autowired
	public StatusRepo status;

	@Autowired
    public Egen_DB(DataSource dataSource) {
        this.setDataSource(dataSource);
	}
	
	//Use DAO pattern with queries for anything not available in repos
}
