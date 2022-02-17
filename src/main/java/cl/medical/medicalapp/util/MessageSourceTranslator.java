package cl.medical.medicalapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceTranslator {
    private final ResourceBundleMessageSource messageSource;

    @Autowired
    MessageSourceTranslator(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.messageSource = resourceBundleMessageSource;
    }

    public String toLocale(String messageCode, Object[]... args) {
        return messageSource.getMessage(messageCode, args, LocaleContextHolder.getLocale());
    }
}