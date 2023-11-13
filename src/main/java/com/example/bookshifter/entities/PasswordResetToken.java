package com.example.bookshifter.entities;

import com.example.bookshifter.utils.TokensExpirationTime;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_password_token")
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    private Date expirationTime;

    public PasswordResetToken(){
    }

    public PasswordResetToken(User user, String token){
        this.user = user;
        this.token = token;
        this.expirationTime = TokensExpirationTime.getExpirantionTime();
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken(){
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpirationTime(){
        return this.expirationTime;
    }

    public void setExpirationTime(Date expirationTime){
        this.expirationTime = expirationTime;
    }
}
