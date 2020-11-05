package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.TransferSqlDAO;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;



@RestController
@RequestMapping("/transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private JdbcTemplate jdbcTemplate;
	private TransferDAO dao;

    public TransferController() {
        this.dao = new TransferSqlDAO(jdbcTemplate);
    }	
	
	
    @RequestMapping( path = "", method = RequestMethod.GET)
    public List<Transfer> viewTransferHistory(User user){
    

        return dao.viewTransfersHistory(user);
    }

    
    
}
