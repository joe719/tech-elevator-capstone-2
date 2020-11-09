package com.techelevator.tenmo.dao;

import java.security.Principal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Account;



@Component
public class AccountSqlDAO implements AccountDAO{
	private JdbcTemplate jdbcTemplate;
	
	public AccountSqlDAO(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;

	}
	@Override
	public double viewCurrentBalance(Principal principal) {
		
		double viewBalance = 0;
		
		String sqlRowSet = "SELECT balance FROM accounts JOIN users ON accounts.user_id = users.user_id WHERE username = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlRowSet, principal.getName());
	
		if (rowSet.next()) {
		viewBalance = rowSet.getDouble(1);
		}
		return viewBalance;
	}
	
	
	
	@Override
	public void sendUpdatesUserBalance (Account sendBucksUpdates) {
	 
		String sql = "UPDATE accounts SET balance = balance - ? " +
					 "FROM users WHERE accounts.user_id = users.user_id AND users.user_id = ?";
		jdbcTemplate.update(sql, sendBucksUpdates.getBalance(), sendBucksUpdates.getSenderUserId());
		
		String sql2 = "UPDATE accounts SET balance = balance + ? " +
				 	"FROM users WHERE accounts.user_id = users.user_id AND users.user_id = ?";
		jdbcTemplate.update(sql2, sendBucksUpdates.getBalance(), sendBucksUpdates.getRecipientUserId());
		
	
	}
	
	

}
