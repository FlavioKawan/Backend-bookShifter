package com.example.bookshifter.controllers;

import com.example.bookshifter.dto.LoginDTO;
import com.example.bookshifter.dto.LoginResponseDTO;
import com.example.bookshifter.entities.User;
import com.example.bookshifter.services.interfaces.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class  LoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenService jwtTokenService;
    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO credentials){
        var usernamePassword= new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        var auth = this.manager.authenticate(usernamePassword);

        var jwtToken =jwtTokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }
}
