package com.example.bookshifter.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class FatecException extends HttpStatusCodeException {
    public FatecException(String message, HttpStatusCode statusCode){
        super(statusCode, message);
    }
}
