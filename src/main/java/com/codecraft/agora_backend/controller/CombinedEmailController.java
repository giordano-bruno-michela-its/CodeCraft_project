package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.service.CombinedEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/newsletter/file")
    public ResponseEntity<InputStreamResource> downloadNewsletterEmailsFile(@RequestBody Map<String, String> requestBody) throws IOException {
        String format = requestBody.get("format");
        String file = combinedEmailService.generateFileForNewsletterEmails(format);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file)
                .contentType(MediaType.parseMediaType("application/" + format))
                .body(resource);
    }
}