package com.techelevator.tenmo.services;

import java.util.List;

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
  


    public Transfer[] viewTransferHistory() throws TransferServiceException{
        Transfer[] Transfers;
        try {
        	Transfers = restTemplate.exchange(BASE_URL + "/transfers", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
        	
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
        return Transfers;
    }
	
    
    
    public Transfer viewTransferDetailsByTransferId(Transfer transferDetails) throws TransferServiceException {
    int id = transferDetails.getTransferId();
   
    try {
    	transferDetails = restTemplate.exchange(BASE_URL + "/transfers/" + id ,HttpMethod.GET, makeTransferEntity(transferDetails), Transfer.class).getBody();
    	
    } catch (RestClientResponseException ex) {
        throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
    }
    return transferDetails;
    
    }
    
    
    
    
	
    public void sendBucksCreatesNewTransfer(Transfer requestBucksNT, AuthenticatedUser currentUser) throws TransferServiceException{
      	 
    	try {
            restTemplate.exchange(BASE_URL + "/transfers/createtransfer", HttpMethod.POST, makeTransferEntityRequestBucks(requestBucksNT), Transfer.class, requestBucksNT).getBody();
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
    	
    } 
    
    
    public void sendUpdatesUserBalance(Transfer sendBucksUpdates, AuthenticatedUser currentUser) throws TransferServiceException{
    	
    	try {
    		restTemplate.exchange(BASE_URL + "/transfers/updates", HttpMethod.PUT, makeTransferEntityRequestBucks(sendBucksUpdates), Transfer.class, sendBucksUpdates).getBody();
        	
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
    	
    }
    
    
    public void requestBucks(Transfer requestBucks, AuthenticatedUser currentUser) throws TransferServiceException{
    	 
    	try {
            restTemplate.exchange(BASE_URL + "/transfers/request", HttpMethod.POST, makeTransferEntityRequestBucks(requestBucks), Transfer.class, requestBucks).getBody();
        } catch (RestClientResponseException ex) {
            throw new TransferServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }
    	
    }
    
    
   
    
    
    
    
//    private HttpEntity<Transfer> makeTransferEntityTransferDetails(Transfer details) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(AUTH_TOKEN);
//        HttpEntity<Transfer> entity = new HttpEntity<>(requestBucks, headers);
//        return entity;
//    }
    
    
    
    
    private HttpEntity<Transfer> makeTransferEntityRequestBucks(Transfer requestBucks) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Transfer> entity = new HttpEntity<>(requestBucks, headers);
        return entity;
    }

    
   
    
    
    private HttpEntity<Transfer> makeTransferEntity(Transfer Transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Transfer> entity = new HttpEntity<>(Transfer, headers);
        return entity;
    }



     
    private HttpEntity<Transfer> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity <Transfer> entity = new HttpEntity<>(headers);
        return entity;
    }
	
	
	
	
}
