package com.tahminapp.auth.event.listener;


import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.event.OnRegistrationCompleteEvent;
import com.tahminapp.auth.service.TranslatorService;
import com.tahminapp.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    //TODO mail entegrasyonu yapilmali
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;


    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }


    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = TranslatorService.toLocale("activation.mail.subject");
        final String confirmationUrl = event.getAppUrl() + "/user/registrationConfirm?token=" + token;
        final String message = TranslatorService.toLocale("activation.mail.message");
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.mail"));
        return email;
    }


}
