package com.techelevator.tenmo.dao;

import java.security.Principal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class AccountSqlDAO implements AccountDAO{
	private JdbcTemplate jdbcTemplate;
	
	public AccountSqlDAO(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;

	}
	@Override
	public double viewCurrentBalance(Principal principal) {
		String sqlRowSet = "SELECT balance FROM accounts JOIN users ON accounts.user_id = users.user_id WHERE username = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlRowSet, principal.getName());
		
		if(rowSet.next()) {
		return rowSet.getDouble("balance");
	}else
		return -1;
	}

}
