package com.techelevator.tenmo.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "User not found.")
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = -1126699074574529145L;

    public UserNotActivatedException(String message) {
        super(message);
    }
}
