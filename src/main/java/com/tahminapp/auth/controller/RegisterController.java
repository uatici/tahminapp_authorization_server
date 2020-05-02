package com.tahminapp.auth.controller;


import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.enums.VerificationTokenStatus;
import com.tahminapp.auth.dto.UserDto;
import com.tahminapp.auth.event.OnRegistrationCompleteEvent;
import com.tahminapp.auth.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

@RestController
public class RegisterController {

    private UserService userService;
    private ApplicationEventPublisher applicationEventPublisher;

    public RegisterController (UserService userService,ApplicationEventPublisher applicationEventPublisher) {

        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping("/user/registration")
    public String showRegistrationForm(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {
        User user = userService.registerNewUserAccount(userDto);
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, null, getAppUrl(request)));
        return "Activasyon emaili hesabınıza gonderilmistir";
    }

    @GetMapping("/user/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") final String token, final HttpServletRequest request) throws UnsupportedEncodingException {
        Locale locale = request.getLocale();
        final VerificationTokenStatus result = userService.validateVerificationToken(token);
        if (result == VerificationTokenStatus.VALID) {
            //TODO bilgi ekrani login ekranina yonlendiren
            return "Hesabiniza giris yapabilirsiniz";
        }

        //TODO invalid yada exripre olmus token
        return "Gecersiz token";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
