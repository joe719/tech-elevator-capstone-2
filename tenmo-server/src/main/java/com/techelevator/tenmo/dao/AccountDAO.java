package com.techelevator.tenmo.dao;

import java.security.Principal;

public interface AccountDAO {

	double viewCurrentBalance(Principal principal);
	
}
