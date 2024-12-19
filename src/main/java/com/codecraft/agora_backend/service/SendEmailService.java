package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.model.FormRichiesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("$(spring.mail.username)")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(FormRichiesta formRichiesta) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(formRichiesta.getEmail());
        message.setText("Email enviado com sucesso!");
        message.setSubject("Richiesta di contatto da "+formRichiesta.getCognome());

        javaMailSender.send(message);
    }
}
