package com.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringSecurityRestApiProjectApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityRestApiProjectApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringSecurityRestApiProjectApplication.class);
    }

}
