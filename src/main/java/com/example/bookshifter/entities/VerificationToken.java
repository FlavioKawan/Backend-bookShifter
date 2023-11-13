package com.example.bookshifter.entities;

import com.example.bookshifter.utils.TokensExpirationTime;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    private Date expirationTime;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public VerificationToken() {

    }
    public VerificationToken(User user, String token){
        this.user = user;
        this.token = token;
        this.expirationTime = TokensExpirationTime.getExpirantionTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
