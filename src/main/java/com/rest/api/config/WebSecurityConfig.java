package com.rest.api.config;

import com.rest.api.security.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> authJwtFilter() {
        FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        // Directly creating an instance
        filterRegistrationBean.setFilter(new JwtFilter());
        // Add URL patterns that need the filter
        filterRegistrationBean.addUrlPatterns("/employees/*");
        filterRegistrationBean.addUrlPatterns("/students/*");
        filterRegistrationBean.addUrlPatterns("/howdy/*");
        // Set the order of the filter if needed
        // filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}
