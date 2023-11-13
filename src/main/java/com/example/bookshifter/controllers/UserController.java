package com.example.bookshifter.controllers;

import com.example.bookshifter.dto.UserAndBookDTO;
import com.example.bookshifter.dto.UserDTO;
import com.example.bookshifter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/books")
    public ResponseEntity<UserAndBookDTO> getUserBooks(@RequestParam("email") String email){
        return ResponseEntity.ok(service.getAuthenticatedUserBooks(email));
    }

}
