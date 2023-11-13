package com.example.bookshifter.controllers;

import com.example.bookshifter.dto.RegisterUserDTO;
import com.example.bookshifter.entities.User;
import com.example.bookshifter.entities.VerificationToken;
import com.example.bookshifter.events.RegistrationCompleteEvent;
import com.example.bookshifter.services.UserServiceImpl;
import com.example.bookshifter.services.VerificationTokenServiceImpl;
import com.example.bookshifter.utils.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private VerificationTokenServiceImpl tokenService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO registerDTO, final HttpServletRequest request){
        User user = service.registerUser(registerDTO);
        publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicationUrl(request)));
        return ResponseEntity.ok("Email enviado com sucesso");
    }


    @GetMapping("/account")
    public ResponseEntity<String> enableAccount(@RequestParam("token") String token){
        Optional<VerificationToken> verificationToken = tokenService.findByToken(token);
        if(verificationToken.isPresent() && verificationToken.get().getUser().isEnabled()){
            return ResponseEntity.ok("Conta ativada com sucesso, por favor faça seu login");
        }

        String validationResult = tokenService.validateToken(token);

        return switch (validationResult.toLowerCase()) {
            case "expired" -> ResponseEntity.status(426).body("Token expirado, por favor gere outro token");
            case "valid" -> ResponseEntity.status(202).body("Token válido, por favor faça seu login");
            default -> ResponseEntity.status(400).body("Token inválido, por favor gere um token válido");
        };
    }
}
