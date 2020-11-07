package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    
    
}
