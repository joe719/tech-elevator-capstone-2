package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.Transfer;




public class TransferService {
	

    public static String AUTH_TOKEN;
    private final String BASE_URL = "http://localhost:8080";
    public RestTemplate restTemplate = new RestTemplate();
  

//	 I moved this method out of App.java and into a transfer service, to keep the structure
//	 more in line with what we've seen in our assignments.

    public Transfer[] viewTransferHistory() throws TransferServiceException{
        Transfer[] Transfers;
        try {
        	Transfers = restTemplate.exchange(BASE_URL + "/transfers", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
        	
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        return Transfers;
    }
	
	
//    The following code can be uncommented if we end up using a transfer in a POST or PUT request
    
    
//    private HttpEntity<Transfer> makeTransferEntity(Transfer Transfer) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(AUTH_TOKEN);
//        HttpEntity<Transfer> entity = new HttpEntity<>(Transfer, headers);
//        return entity;
//    }


    //helper method for GET requests
     
    private HttpEntity<Transfer> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity <Transfer> entity = new HttpEntity<>(headers);
        return entity;
    }
	
	
	
	
}
