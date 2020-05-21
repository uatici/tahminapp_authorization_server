package com.tahminapp.auth.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LocaleResolverConfiguration implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        CustomLocaleResolver clr = new CustomLocaleResolver();
        return clr;
    }

    @Bean()
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource rs = new ReloadableResourceBundleMessageSource();
        rs.setBasename("classpath:/locales/messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setFallbackToSystemLocale(true);
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }
}
