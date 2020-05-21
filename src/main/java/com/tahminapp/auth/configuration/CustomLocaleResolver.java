package com.tahminapp.auth.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CustomLocaleResolver implements LocaleResolver {

  List<Locale> LOCALES = Arrays.asList(
          new Locale("en"),
          new Locale("tr"));

  @Override
  public Locale resolveLocale(HttpServletRequest request) {
    String lang = request.getParameter("lang");
    if(StringUtils.isBlank(lang)) {
      lang = request.getHeader("Accept-Language");
    }
    return StringUtils.isBlank(lang)
            ? Locale.getDefault()
            : Locale.lookup(Locale.LanguageRange.parse(lang), LOCALES);
  }

  @Override
  public void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale) {
    throw new UnsupportedOperationException("Cannot change HTTP accept header - use a different locale resolution strategy");
  }

}
