package com.tahminapp.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TranslatorService {

  private static MessageSource messageSource;

  @Autowired
  TranslatorService(MessageSource messageSource) {
    TranslatorService.messageSource = messageSource;
  }

  public static String toLocale(String msgCode) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(msgCode, null, locale);
  }
}
