package com.arturbik.licensingservice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocalMessagesConfig {

    @Bean
    public SessionLocaleResolver localResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.of("ru_RU"));
        localeResolver.setDefaultLocale(Locale.of("en"));
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultLocale(Locale.of("en"));
        return messageSource;
    }

}
