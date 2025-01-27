package com.codecraft.agora_backend.config;

import com.codecraft.agora_backend.model.AdminEmails;
import com.codecraft.agora_backend.service.AdminEmailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Optional;
import java.util.Properties;

/**
 * Configuration class for setting up email sender.
 */
@Configuration
public class EmailConfig {

    private final Optional<AdminEmails> adminEmails;

    /**
     * Constructor for EmailConfig.
     *
     * @param adminEmailsService the service to retrieve admin email settings
     */
    public EmailConfig(AdminEmailsService adminEmailsService) {
        this.adminEmails = adminEmailsService.getAdminEmailsById(1L);
    }

    /**
     * Bean for JavaMailSender.
     *
     * @return a configured JavaMailSender instance
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        if (adminEmails.isPresent()) {
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);  // Replace with your SMTP port
            mailSender.setUsername(adminEmails.get().getNoReplyEmail());
            mailSender.setPassword(adminEmails.get().getNoReplyPassword());
            mailSender.setProtocol("smtp");
            mailSender.setJavaMailProperties(getMailProperties());
        }
        return mailSender;
    }

    /**
     * Get mail properties.
     *
     * @return a Properties object containing mail properties
     */
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return properties;
    }
}

