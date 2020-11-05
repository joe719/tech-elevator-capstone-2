package com.techelevator.tenmo.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.model.Account;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountDAO dao;
	private JdbcTemplate jdbc = new JdbcTemplate();
	
	public AccountController() {
		this.dao = new AccountSqlDAO(jdbc);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public double viewCurrentBalance(@PathVariable long id) throws AccountNotFoundException {
	
		return dao.viewCurrentBalance(id);
	}
	
	

}
