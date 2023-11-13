package com.example.bookshifter.controllers;

import com.example.bookshifter.dto.PasswordResetDTO;
import com.example.bookshifter.entities.User;
import com.example.bookshifter.events.PasswordRecoveryEvent;
import com.example.bookshifter.services.PasswordResetTokenServiceImpl;
import com.example.bookshifter.services.UserServiceImpl;
import com.example.bookshifter.utils.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/forgot-password")
public class PasswordResetController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PasswordResetTokenServiceImpl service;

    @PostMapping
    public ResponseEntity<String> sendResetPasswordRequest(@RequestBody PasswordResetDTO dto, HttpServletRequest request){
        String email = dto.getEmail();
        Optional<User> user = userService.findByEmail(email);

        if(user.isEmpty()){
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        publisher.publishEvent(new PasswordRecoveryEvent(user.get(), UrlUtil.getApplicationUrl(request)));
        return  ResponseEntity.status(200).body("Email de mudança de senha enviado");
    }

    @PostMapping("/password-reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDTO request, @RequestParam("token") String requestToken){
        String validationTokenResult = service.validateToken(requestToken);
        String newPassword = request.getNewPassword();
        if(validationTokenResult.equalsIgnoreCase("valid")){
            Optional<User> user = service.findUserByToken(requestToken);

            if(user.isPresent()){
                if (checkValidation(newPassword, request.getNewPasswordConfirmation()).equalsIgnoreCase("valid")){
                    service.resetPassword(user.get(), newPassword);
                    return ResponseEntity.status(200).body("Senha alterada com sucesso");
                } else ResponseEntity.status(409).body("AS senhas não coincidem");
            }
        }

        return ResponseEntity.status(406).body("Token não pertence a nenhum usuário ou expirou");
    }

    private String checkValidation(String newPassword, String newPasswordConfirmation){
        if(newPassword.equalsIgnoreCase(newPasswordConfirmation)){
            return "VALID";
        }
        return "INVALID";
    }
}
