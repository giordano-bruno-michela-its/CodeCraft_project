package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.model.*;
import com.codecraft.agora_backend.model.FormBooking;
import com.codecraft.agora_backend.model.FormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
            message.setSubject("Cascina Caccia: conferma di ricezione della richiesta di informazioni");
            message.setText("Ciao " + formInfo.getName() + ","
                            + "\ngrazie per averci contattato e aver mostrato interesse nelle attività di Cascina Caccia dedicate a bambini e ragazzi."
                            + "\nAbbiamo ricevuto la tua comunicazione di richiesta di informazioni. Risponderemo ai tuoi dubbi e alle tue domande entro 3 giorni lavorativi per permetterti di organizzare al meglio."
                            + "\nNel frattempo ti invitiamo a iscriverti alla nostra newsletter gratuita per avere aggiornamenti sulle attività di Cascina Caccia e per ricevere materiale di formazione e approfondimento."
                            + "\nPer iscriverti alla newsletter andare al seguente link: [Nome Link]"
                            + "\nA presto!"
                            + "\nI volontari di Cascina Caccia");
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
            message.setSubject("Conferma Prenotazione - Codice "+formBooking.getUniqueCode());
            message.setText("Ciao "+formBooking.getName() + ","
                            + "\nsiamo lieti di confermare la prenotazione per il tuo gruppo in data da "+formBooking.getBeginTime()+" a "+formBooking.getEndTime()+". Il tuo codice di prenotazione è 235468. Lo potrai inserire nella sezione dedicata per modificare o correggere le informazioni che ci hai comunicato."
                            + "\nNei prossimi giorni uno dei nostri volontari ti contatterà per discutere al meglio i dettagli della tua permanenza in Cascina."
                            + "\nNel frattempo ti consigliamo di dare un'occhiata alla sezione FAQ del nostro sito per rispondere a eventuali dubbi o domande."
                            + "\nPer modificare le informazioni inserite nel form andare al seguente link: [Nome Link]"
                            + "\nTi aspettiamo in Cascina!"
                            + "\nI volontari di Cascina Caccia");
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
            message.setSubject("Agorà - Nuova Richiesta Infomazioni (" + formInfo.getAssociation()+")");
            message.setText("Gentili Amministratori,"
                            + "\nÈ stata inviata una nuova richiesta di informazioni tramite il sito. Di seguito i dettagli:"
                            + "\nDati del richiedente:"
                            + "\n   • Nome e Cognome: "+formInfo.getName()+" "+formInfo.getSurname()
                            + "\n   • Ente: "+formInfo.getAssociation()
                            + "\n   • E-mail: "+formInfo.getEmail()
                            + "\n   • Cellulare: "+formInfo.getPhoneNumber()
                            + "\nDettagli della richiesta:"
                            + "\n   • Attività: " + printActivity(formInfo.getActivityType())
                            + "\n   • Messaggio: " + formInfo.getAdditionalInfo()
                            + "\nDettagli invio:"
                            + "\n   • Richiesta effettuata il "+formInfo.getContactDate());
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
            message.setSubject("Agorà: Nuova Prenotazione - Codice "+formBooking.getUniqueCode());
            message.setText("Gentili Amministratori," +
                            "\nÈ stata effettuata una nuova prenotazione tramite il sito. Di seguito i dettagli:" +
                            "\nDati del richiedente:" +
                            "\n   • Nome e Cognome: "+formBooking.getName()+" "+formBooking.getSurname()+
                            "\n   • Ente: "+formBooking.getAssociation()+
                            "\n   • E-mail: "+formBooking.getEmail()+
                            "\n   • Cellulare: "+formBooking.getPhoneNumber()+
                            "\nInformazioni sulla visita:"+
                            "\n   • Periodo di disponibilità: da "+formBooking.getBeginTime()+ " a "+formBooking.getEndTime()+
                            "\n   • Numero bambini e ragazzi: "+formBooking.getParticipantsQuantity()+
                            "\n   • Numero accompagnatori: "+formBooking.getGuidesQuantity()+
                            "\n   • Attività scelte: "+printActivity(formBooking.getActivityType())+
                            "\n   • Messaggio: "+formBooking.getAdditionalInfo()+
                            "\nDettagli prenotazione: "+
                            "\n   • Prenotazione effettuata il "+formBooking.getContactDate());
        }
        mailSender.send(message);
    }

    public String printActivity (Set<ActivityType> activity) {
        StringBuilder activityString = new StringBuilder();
        for (ActivityType activityType : activity) {
            activityString.append(activityType.getName()).append("    ");
        }
        return activityString.toString();
    }
}
