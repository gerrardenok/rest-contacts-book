package org.itechart.config;

import org.itechart.service.UserService;
import org.itechart.service.internal.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

}