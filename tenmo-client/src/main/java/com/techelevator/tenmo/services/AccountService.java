package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.Account;

public class AccountService {
	public static String AUTH_TOKEN;
	private final String BASE_URL = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();
	

	
	public double viewCurrentBalance() throws AccountServiceException{
		Account balanceAccount;
       
        try {
            balanceAccount = restTemplate.exchange(BASE_URL + "/accounts", HttpMethod.GET, makeAuthEntity(AUTH_TOKEN), Account.class).getBody();
            
        } catch (RestClientResponseException ex) {
            throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        
        return balanceAccount.getBalance();
	}
	
   
    private HttpEntity <Account> makeAuthEntity(String AUTH_TOKEN) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity <Account> entity = new HttpEntity<>(headers);
        return entity;
    }
	
}
