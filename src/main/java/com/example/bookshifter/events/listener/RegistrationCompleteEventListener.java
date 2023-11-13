package com.example.bookshifter.events.listener;

import com.example.bookshifter.entities.User;
import com.example.bookshifter.events.RegistrationCompleteEvent;
import com.example.bookshifter.services.interfaces.VerificationTokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private VerificationTokenService tokenService;

    private final JavaMailSender mailSender;
    private User user;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        user = event.getUser();

        String verificationToken = UUID.randomUUID().toString();
        tokenService.saveVerificationToken(user, verificationToken);

        String url = "http://localhost:8000/register/enable?token=" + verificationToken;
        try {
            sendActivationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendActivationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Ative sua conta";
        String senderName = "Bookshifter";
        String content = "<p> Olá "+ user.getFirstName() + ". Obrigado por fazer parte do Bookshifter!</p>"
                + "<p>Por favor, clique no link abaixo para ativar sua conta.</p>"
                + "<a href=\"" + url + "\">Ative sua conta</a>"
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
