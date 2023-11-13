package com.example.bookshifter.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class BookException extends HttpStatusCodeException {
    public BookException(String messsage, HttpStatusCode statusCode){
        super(statusCode, messsage);
    }
}
