package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.model.FormPrenotazione;
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

    //This method sends an email to request more information
    public void sendEmailInformation(FormRichiesta formInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(formInfo.getEmail());
        message.setText("Email enviado com sucesso!");
        message.setSubject("Richiesta di contatto da "+formInfo.getCognome());

        mailSender.send(message);
    }

    //This method sends an email to request a booking
    public void sendEmailBooking(FormPrenotazione formBooking) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(formBooking.getEmail());
        message.setText("Email enviado com sucesso!");
        message.setSubject("Richiesta di prenotazione da "+formBooking.getCognome());

        mailSender.send(message);
    }
}
