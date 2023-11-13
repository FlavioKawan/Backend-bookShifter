package com.example.bookshifter.dto;

import lombok.Data;

@Data
public class PasswordResetDTO {
    private String email;
    private String newPassword;
    private String newPasswordConfirmation;

    public PasswordResetDTO(){

    }
    public PasswordResetDTO(String newPassword){
        this.newPassword = newPassword;

    }

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewPasswordConfirmation() {
        return newPasswordConfirmation;
    }
}
