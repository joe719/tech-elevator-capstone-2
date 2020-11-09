package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;

public class AccountService {
	public static String AUTH_TOKEN;
	private final String BASE_URL = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();
	

	
	public double viewCurrentBalance() throws AccountServiceException{
		Account balanceAccount;
       
        try {
            balanceAccount = restTemplate.exchange(BASE_URL + "/accounts", HttpMethod.GET, makeAuthEntity(), Account.class).getBody();
            
        } catch (RestClientResponseException ex) {
            throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        
        return balanceAccount.getBalance();
	}
	
	
		public void sendUpdatesUserBalance(Account updatedAccount, AuthenticatedUser currentUser) throws TransferServiceException{
    	
    	try {
    		restTemplate.exchange(BASE_URL + "/accounts/updates", HttpMethod.PUT, makeAccountEntity(updatedAccount), Transfer.class, updatedAccount).getBody();
        	
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
    	
    }
	
	
    private HttpEntity <Account> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity <Account> entity = new HttpEntity<>(headers);
        return entity;
    }
    
    
    private HttpEntity<Account> makeAccountEntity(Account updatedAccount) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.setBearerAuth(AUTH_TOKEN);
    	HttpEntity<Account> entity = new HttpEntity<>(updatedAccount, headers);
    	return entity;
}
    
	
}
