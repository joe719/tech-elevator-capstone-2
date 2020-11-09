package com.techelevator.tenmo.dao;

import java.security.Principal;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {

	double viewCurrentBalance(Principal principal);

	void sendUpdatesUserBalance(Account sendBucksUpdates);
	
}
