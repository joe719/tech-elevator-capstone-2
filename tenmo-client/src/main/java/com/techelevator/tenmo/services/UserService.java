package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.User;

public class UserService {

    public static String AUTH_TOKEN;
    private final String BASE_URL = "http://localhost:8080/users";
    public RestTemplate restTemplate = new RestTemplate();
	
    public User[] listAllUsers() throws UserServiceException{
        User[] Users;
        try {
        	Users = restTemplate.exchange(BASE_URL, HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
        	
        } catch (RestClientResponseException ex) {
            throw new UserServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        return Users;
    }
	
    
    private HttpEntity<User> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity <User> entity = new HttpEntity<>(headers);
        return entity;
    }
    
    
    
}
