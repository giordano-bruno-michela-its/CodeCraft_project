package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.model.*;
import com.codecraft.agora_backend.model.FormBooking;
import com.codecraft.agora_backend.model.FormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SendEmailService {

    @Autowired
    public JavaMailSender mailSender;

    @Autowired
    private AdminEmailsService adminEmailsService;

    private Optional<AdminEmails> adminEmails;

    //Constructor for adminEmails, which sets the email to the object in the database with id = 1
    public SendEmailService(AdminEmailsService adminEmailsService) {
        this.adminEmails = adminEmailsService.getAdminEmailsById(1L);
    }

    public void updateAdminEmails() {
        this.adminEmails = adminEmailsService.getAdminEmailsById(1L);
    }

    //This method sends an email to the user to confirm the data in the information request
    public void sendEmailInformation(FormInfo formInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        updateAdminEmails();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(formInfo.getEmail());
            message.setSubject("Richiesta di informazioni a Cascina Caccia");
            message.setText(formInfo.getName()+ " "+ formInfo.getSurname()+"\n"+formInfo.getAdditionalInfo());
        }
        mailSender.send(message);
    }

    //This method sends an email to the user to confirm the data in the booking request
    public void sendEmailBooking(FormBooking formBooking) {
        SimpleMailMessage message = new SimpleMailMessage();
        updateAdminEmails();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(formBooking.getEmail());
            message.setSubject("Richiesta di prenotazione a Cascina Caccia");
            message.setText(formBooking.getName()+ " "+ formBooking.getSurname()+"\n"+formBooking.getAdditionalInfo());
        }
        mailSender.send(message);
    }

    //This method sends an email to the admin to inform about for a new information request
    public void sendInfoToAdmin(FormInfo formInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        updateAdminEmails();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(adminEmails.get().getAdminEmail());
            message.setSubject("Richiesta di informazioni da "+formInfo.getSurname());
            message.setText(formInfo.getName()+ " "+ formInfo.getSurname()+"\n"+formInfo.getAdditionalInfo());
        }
        mailSender.send(message);
    }

    //This method sends an email to the admin to inform about for a new booking request
    public void sendBookingToAdmin(FormBooking formBooking) {
        SimpleMailMessage message = new SimpleMailMessage();
        updateAdminEmails();
        if(adminEmails.isPresent()) {
            message.setFrom(adminEmails.get().getNoReplyEmail());
            message.setTo(adminEmails.get().getAdminEmail());
            message.setSubject("Richiesta di prenotazione da " + formBooking.getSurname());
            message.setText(formBooking.getName()+ " "+ formBooking.getSurname()+"\n"+formBooking.getAdditionalInfo());
        }
        mailSender.send(message);
    }
}
