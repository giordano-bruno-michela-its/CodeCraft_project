package com.codecraft.agora_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Service class for managing newsletter email operations.
 * This class provides methods for getting all emails from all forms with newsletterCheck set to "YES",
 * showing all emails in the console, and generating a file with all emails for newsletter.
 */
@Service
public class CombinedEmailService {

    private final FormInfoService formInfoService;
    private final FormNewsletterService formNewsletterService;

    /**
     * Constructor for CombinedEmailService.
     *
     * @param formInfoService       the service for form information
     * @param formNewsletterService the service for form newsletters
     */
    @Autowired
    public CombinedEmailService(FormInfoService formInfoService, FormNewsletterService formNewsletterService) {
        this.formInfoService = formInfoService;
        this.formNewsletterService = formNewsletterService;
    }

    /**
     * Get all emails for newsletter subscription from both form information/booking and form newsletters.
     *
     * @return the list of all emails for newsletter subscription
     */
    public List<String> getAllEmailsForNewsletter() {
        List<String> formInfoEmails = formInfoService.getEmailsForNewsletter();
        List<String> formNewsletterEmails = formNewsletterService.getEmailsForNewsletter();
        formInfoEmails.addAll(formNewsletterEmails);
        return formInfoEmails;
    }

    /**
     * Generate a file containing all newsletter emails in the specified format.
     *
     * @param format the format of the file (csv or txt)
     * @return the path to the generated file
     * @throws IOException if an I/O error occurs
     */
    public String generateFileForNewsletterEmails(String format) throws IOException {
        List<String> emails = getAllEmailsForNewsletter();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String folderPath = "src/main/resources/newsletterlogs/";
        Files.createDirectories(Paths.get(folderPath));
        String fileName = folderPath + "newsletter_emails_" + timestamp + "." + format;
        try (FileWriter writer = new FileWriter(fileName)) {
            if ("csv".equalsIgnoreCase(format)) {
                writer.append("Email\n");
                for (String email : emails) {
                    writer.append(email).append("\n");
                }
            } else if ("txt".equalsIgnoreCase(format)) {
                for (String email : emails) {
                    writer.append(email).append("\n");
                }
            } else {
                throw new IllegalArgumentException("Unsupported file format: " + format);
            }
        }
        return fileName;
    }
}