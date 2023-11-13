package com.example.bookshifter.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class UserNotFoundException extends HttpStatusCodeException {
    public UserNotFoundException(String message, HttpStatusCode statusCode){
        super(statusCode, message);
    }
}
