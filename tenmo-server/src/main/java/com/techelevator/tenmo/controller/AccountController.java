package com.techelevator.tenmo.controller;

import java.security.Principal;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.model.Account;



@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountDAO dao;
	
	public AccountController(JdbcTemplate jdbc) {
		this.dao = new AccountSqlDAO(jdbc);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public double viewCurrentBalance(Principal principal) throws AccountNotFoundException {
	
		return dao.viewCurrentBalance(principal);
	}
	
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping( path = "/updates", method = RequestMethod.PUT)
    
    public void sendUpdatesUserBalance(@RequestBody Account sendBucksUpdates) {
    
    	dao.sendUpdatesUserBalance(sendBucksUpdates);
    }
    
	

}
