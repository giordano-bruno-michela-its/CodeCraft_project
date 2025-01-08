package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.model.*;
import com.codecraft.agora_backend.model.FormBooking;
import com.codecraft.agora_backend.model.FormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;

@Service
public class SendEmailService {

    @Autowired
    public JavaMailSender mailSender;

    private final Optional<AdminEmails> adminEmails;

    public SendEmailService(AdminEmailsService adminEmailsService) {
        this.adminEmails = adminEmailsService.getAdminEmailsById(1L);
    }

    //This method sends an email to request more information
    public void sendEmailInformation(FormInfo formInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(formInfo.getEmail());
            message.setSubject("Richiesta di informazioni a Cascina Caccia");
            message.setText(formInfo.getName()+ " "+ formInfo.getSurname()+"\n"+formInfo.getAdditionalInfo());
        }
        mailSender.send(message);
    }

    //This method sends an email to request a booking
    public void sendEmailBooking(FormBooking formBooking) {
        SimpleMailMessage message = new SimpleMailMessage();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(formBooking.getEmail());
            message.setSubject("Richiesta di prenotazione a Cascina Caccia");
            message.setText(formBooking.getName()+ " "+ formBooking.getSurname()+"\n"+formBooking.getAdditionalInfo());
        }
        mailSender.send(message);
    }

    public void sendInfoToAdmin(FormInfo formInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(adminEmails.get().getAdminEmail());
            message.setSubject("Richiesta di informazioni da "+formInfo.getSurname());
            message.setText(formInfo.getName()+ " "+ formInfo.getSurname()+"\n"+formInfo.getAdditionalInfo());
        }
    }

    public void sendBookingToAdmin(FormBooking formBooking) {
        SimpleMailMessage message = new SimpleMailMessage();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(adminEmails.get().getAdminEmail());
            message.setSubject("Richiesta di prenotazione da " + formBooking.getSurname());
            message.setText(formBooking.getName()+ " "+ formBooking.getSurname()+"\n"+formBooking.getAdditionalInfo());
        }
    }
}
