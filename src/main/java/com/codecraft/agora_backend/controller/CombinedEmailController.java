package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.service.CombinedEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "http://localhost:3000")
public class CombinedEmailController {

    private final CombinedEmailService combinedEmailService;

    @Autowired
    public CombinedEmailController(CombinedEmailService combinedEmailService) {
        this.combinedEmailService = combinedEmailService;
    }

    @GetMapping("/newsletter")
    public List<String> getAllEmailsForNewsletter() {
        return combinedEmailService.getAllEmailsForNewsletter();
    }
}