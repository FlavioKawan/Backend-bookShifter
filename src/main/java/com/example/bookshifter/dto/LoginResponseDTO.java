package com.example.bookshifter.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    String token;

    public LoginResponseDTO(){

    }

    public LoginResponseDTO(String token){
        this.token = token;
    }
}
