package com.example.bookshifter.services.interfaces;


import com.example.bookshifter.entities.User;

public interface JwtTokenService {
    String generateToken(User user);
    String validateToken(String token);
}
