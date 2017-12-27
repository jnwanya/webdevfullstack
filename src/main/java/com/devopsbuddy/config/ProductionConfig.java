package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/devopsbuddy/application-prod.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
