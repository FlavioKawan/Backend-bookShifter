package com.example.bookshifter.dto;

import com.example.bookshifter.entities.Role;
import com.example.bookshifter.entities.User;

import java.util.List;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;

    public UserDTO(){

    }

    public UserDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().toList();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
