package com.tahminapp.auth.event;

import com.tahminapp.auth.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;


@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}
