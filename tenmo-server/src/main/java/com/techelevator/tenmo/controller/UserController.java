package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.User;

@RestController
@PreAuthorize("isAuthenticated()")

public class UserController {
	
	private UserDAO dao;
	

    public UserController(JdbcTemplate jdbcTemplate) {
        this.dao = new UserSqlDAO(jdbcTemplate);
    }

 
    
    
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> findAll() {
        return dao.findAll();
    }
    
}
