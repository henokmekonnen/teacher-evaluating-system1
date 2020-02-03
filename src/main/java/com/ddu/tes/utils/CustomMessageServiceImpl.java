package com.ddu.tes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomMessageServiceImpl  implements  CustomMessageService{

    @Autowired
    private MessageSource messageSource;

    Locale locale = LocaleContextHolder.getLocale();

    @Override
    public String getMessage(String key, String[]args) {
        return messageSource.getMessage(key,args, null);
    }

    @Override
    public String getMessage(String key) {
        return messageSource.getMessage(key,null,locale);
    }
}
