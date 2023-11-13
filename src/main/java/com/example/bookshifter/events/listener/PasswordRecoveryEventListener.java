package com.example.bookshifter.events.listener;

import com.example.bookshifter.entities.User;
import com.example.bookshifter.events.PasswordRecoveryEvent;
import com.example.bookshifter.services.PasswordResetTokenServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PasswordRecoveryEventListener implements ApplicationListener<PasswordRecoveryEvent> {


    private final PasswordResetTokenServiceImpl service;
    private final JavaMailSender mailSender;
    private User user;

    @Override
    public void onApplicationEvent(PasswordRecoveryEvent event) {
        user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.saveResetPasswordToken(user, token);
        String url = "http://localhost:8000/password-reset?token=" + token;

        try {
            sendResetPasswordEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendResetPasswordEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Recupere sua senha";
        String senderName = "Bookshifter";
        String content = "<p> Olá "+ user.getFirstName() + "</p>"
                + "<p><strong> Foi requisitado uma mudança de senha para a conta atrelada a este email no Bookshifter" +
                ".</strong></p>"
                +  "<p> Clique no link abaixo para recuperar sua senha</p>"
                + "<a href=\"" + url + "\">Recupere sua senha</a>"
                + "<p>&copy; Bookshifter</p>";
        emailMessage(subject, senderName, content, mailSender, user);
    }
    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("book.shifter.brazil@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

}
