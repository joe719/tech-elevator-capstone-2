package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.Account;

public class AccountService {
	public static String AUTH_TOKEN = "";
	private String BASE_URL = "htttp://loalhost:8080/";
	private RestTemplate restTemplate = new RestTemplate();
	
	public AccountService() {
			
	}
	
	public double viewCurrentBalance() throws AccountServiceException{
		double balance;
       
        try {
            balance = restTemplate
                    .exchange(BASE_URL + "accounts", HttpMethod.GET, makeAuthEntity(), double.class).getBody();
        } catch (RestClientResponseException ex) {
            throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        return balance;
	}
	
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
	
}
