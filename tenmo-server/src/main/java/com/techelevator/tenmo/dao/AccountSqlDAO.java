package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AccountSqlDAO implements AccountDAO{
	  private JdbcTemplate jdbcTemplate;
	  
	  public AccountSqlDAO(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;

	}
	@Override
	public double viewCurrentBalance(long accountId) {
		String sqlRowSet = "SELECT balance FROM accounts WHERE account_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlRowSet, accountId);
		
		return rowSet.getDouble("balance");
	}
	

}
