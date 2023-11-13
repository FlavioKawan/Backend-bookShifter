package com.example.bookshifter.events;

import com.example.bookshifter.entities.User;
import org.springframework.context.ApplicationEvent;

public class PasswordRecoveryEvent extends ApplicationEvent {
    private User user;
    private String confirmationURL;

    public PasswordRecoveryEvent(User user, String confirmationUrl) {
        super(user);
        this.user = user ;
        this.confirmationURL = confirmationUrl;
    }

    public User getUser() {
        return user;
    }

    public void setRegisterUserDTO(User user) {
        this.user = user;
    }

    public String getConfirmationURL() {
        return confirmationURL;
    }

    public void setConfirmationURL(String confirmationURL) {
        this.confirmationURL = confirmationURL;
    }
}
