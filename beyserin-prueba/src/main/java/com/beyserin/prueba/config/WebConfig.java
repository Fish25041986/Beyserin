package com.beyserin.prueba.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	
    @Bean
    public FilterRegistrationBean<UrlFilter> urlFilter() {
        FilterRegistrationBean<UrlFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UrlFilter());
        registrationBean.addUrlPatterns("/*"); // Aplico el filtro a todas las URLs
        return registrationBean;
    }

}
