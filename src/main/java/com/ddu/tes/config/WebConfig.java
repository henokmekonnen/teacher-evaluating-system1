package com.ddu.tes.config;

import com.ddu.tes.interceptors.CustomSessionInterceptor;
import com.ddu.tes.interceptors.SessionHandelerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CustomSessionInterceptor customSessionInterceptor;

    @Autowired
    private SessionHandelerInterceptor sessionHandelerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customSessionInterceptor);
        registry.addInterceptor(sessionHandelerInterceptor);


    }

}
