package com.codecraft.agora_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombinedEmailService {

    private final FormInfoService formInfoService;
    private final FormNewsletterService formNewsletterService;

    @Autowired
    public CombinedEmailService(FormInfoService formInfoService, FormNewsletterService formNewsletterService) {
        this.formInfoService = formInfoService;
        this.formNewsletterService = formNewsletterService;
    }

    public List<String> getAllEmailsForNewsletter() {
        List<String> formInfoEmails = formInfoService.getEmailsForNewsletter();
        List<String> formNewsletterEmails = formNewsletterService.getEmailsForNewsletter();
        formInfoEmails.addAll(formNewsletterEmails);
        return formInfoEmails;
    }
}