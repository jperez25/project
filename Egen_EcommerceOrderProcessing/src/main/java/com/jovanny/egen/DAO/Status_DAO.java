package com.jovanny.egen.DAO;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jovanny.egen.Models.Status;

import javax.sql.DataSource;

@Repository
@Transactional
public class Status_DAO extends JdbcDaoSupport {

	@Autowired
    public Status_DAO(DataSource dataSource) {
        this.setDataSource(dataSource);
	}
	
	public String getSatus(long id) {
		String sql = "SELECT * FROM egenorderprocessing.status where status_id = ?;";
		
		Object[] params = new Object[] { id };
		
		Status str = this.getJdbcTemplate().queryForObject(sql, params, new BeanPropertyRowMapper<Status>(Status.class));
		
		System.out.println(str.getStatus_name());	
		
		return str.getStatus_name();
		
	}
}
