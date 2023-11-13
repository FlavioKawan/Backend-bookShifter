package com.example.bookshifter.services.interfaces;

import com.example.bookshifter.entities.User;
import com.example.bookshifter.entities.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {
    String validateToken(String token);

    void saveVerificationToken(User user, String token);

    Optional<VerificationToken> findByToken(String token);
}
