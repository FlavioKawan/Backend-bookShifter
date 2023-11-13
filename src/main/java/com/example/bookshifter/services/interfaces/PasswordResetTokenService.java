package com.example.bookshifter.services.interfaces;

import com.example.bookshifter.entities.User;

import java.util.Optional;

public interface PasswordResetTokenService {
    void saveResetPasswordToken(User user, String token);

    String validateToken(String token);

    Optional<User> findUserByToken(String token);

    void resetPassword(User user, String newPassword);


}
