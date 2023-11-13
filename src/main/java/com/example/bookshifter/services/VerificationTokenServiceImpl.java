package com.example.bookshifter.services;

import com.example.bookshifter.entities.User;
import com.example.bookshifter.entities.VerificationToken;
import com.example.bookshifter.repositories.UserRepository;
import com.example.bookshifter.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
@Service
public class VerificationTokenServiceImpl implements com.example.bookshifter.services.interfaces.VerificationTokenService {
    @Autowired
    private VerificationTokenRepository repository;
    @Autowired
    private UserRepository userRepository;
    public String validateToken(String token){
        Optional<VerificationToken> tokenToBeVerified = repository.findByToken(token);

        if(tokenToBeVerified.isEmpty()){
            return "INVALID";
        }

        User user = tokenToBeVerified.get().getUser();

        Calendar calendar = Calendar.getInstance();

        if((tokenToBeVerified.get().getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            return "EXPIRED";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "VALID";

    }

    public void saveVerificationToken(User user, String token){
        VerificationToken verificationToken = new VerificationToken(user, token);
        repository.save(verificationToken);
    }

    public Optional<VerificationToken> findByToken(String token){
        return repository.findByToken(token);
    }
}
