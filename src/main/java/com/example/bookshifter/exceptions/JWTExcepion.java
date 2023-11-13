package com.example.bookshifter.exceptions;

public class JWTExcepion extends RuntimeException {
    public JWTExcepion(String message){
        super(message);
    }
}
