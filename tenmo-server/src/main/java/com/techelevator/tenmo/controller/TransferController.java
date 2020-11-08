package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.TransferSqlDAO;
import com.techelevator.tenmo.model.Transfer;




@RestController
@RequestMapping("/transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {

   
	private TransferDAO dao;

    public TransferController(JdbcTemplate jdbcTemplate) {
        this.dao = new TransferSqlDAO(jdbcTemplate);
    }	
	
	
    @RequestMapping( path = "", method = RequestMethod.GET)
    public List<Transfer> viewTransferHistory(Principal principal){
   

        return dao.viewTransfersHistory(principal);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Transfer viewTransferDetailsByTransferId(Transfer transferDetails) {
    	return dao.viewTransferDetailsByTransferId(transferDetails);
    }
    
    
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping( path = "/updates", method = RequestMethod.PUT)
    
    public void sendUpdatesUserBalance(Transfer  sendBucksUpdates) {
    
    	dao.sendUpdatesUserBalance(sendBucksUpdates);
    }
    
    
    //adds a transfer when send method
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/createtransfer", method = RequestMethod.POST)
    public void sendBucksCreatesNewTransfer(@RequestBody Transfer requestBucksNT) {
    	dao.sendBucksCreatesNewTransfer(requestBucksNT);
    }
    
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/request", method = RequestMethod.POST)
    
    public void requestBucks(@RequestBody Transfer requestBucks) {
    
    	dao.requestBucks(requestBucks);
    }
    
}
