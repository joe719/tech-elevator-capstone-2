package com.techelevator.tenmo.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;




public class TransferService {
	

    public static String AUTH_TOKEN;
    private final String BASE_URL = "http://localhost:8080";
    public RestTemplate restTemplate = new RestTemplate();
  

	
    public void sendBucksCreatesNewTransfer(Transfer requestBucksNT, AuthenticatedUser currentUser) throws TransferServiceException{
      	 
    	try {
            restTemplate.exchange(BASE_URL + "/transfers/createtransfer", HttpMethod.POST, makeTransferEntityRequestBucks(requestBucksNT), Transfer.class, requestBucksNT).getBody();
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
    	
    } 

    
    public Transfer[] viewTransferHistory() throws TransferServiceException{
        Transfer[] Transfers;
        try {
        	Transfers = restTemplate.exchange(BASE_URL + "/transfers", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
        	
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        return Transfers;
    }
	
    
    
    public Transfer viewTransferDetailsByTransferId(int id) throws TransferServiceException {
    	 Transfer myTransfer;
    	   
    	  try {
    	    	myTransfer = restTemplate.exchange(BASE_URL + "/transfers/" + id ,HttpMethod.GET, makeTransferEntity(), Transfer.class).getBody();
    	    	
    	  } catch (RestClientResponseException ex) {
    	        throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
    	  }
    	  return myTransfer;
    	    
    	  }
    

    public void requestBucks(Transfer requestBucks, AuthenticatedUser currentUser) throws TransferServiceException{
    	 
    	try {
            restTemplate.exchange(BASE_URL + "/transfers/request", HttpMethod.POST, makeTransferEntityRequestBucks(requestBucks), Transfer.class, requestBucks).getBody();
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
    	
    }
    
    public Transfer[] viewPendingRequests() throws TransferServiceException{
        Transfer[] pendingTransfers;
        try {
        	pendingTransfers = restTemplate.exchange(BASE_URL + "/transfers/pendingrequests", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
        	
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        return pendingTransfers;
    }
   

    
    
    
    
    private HttpEntity<Transfer> makeTransferEntityRequestBucks(Transfer requestBucks) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Transfer> entity = new HttpEntity<>(requestBucks, headers);
        return entity;
    }

    
    private HttpEntity<Transfer> makeTransferEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Transfer> entity = new HttpEntity<>(headers);
        return entity;
    }
    
     
    private HttpEntity<Transfer> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity <Transfer> entity = new HttpEntity<>(headers);
        return entity;
    }
	
	
	
	
}
