package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.service.CombinedEmailService;
import io.swagger.v3.oas.annotations.media.Schema;
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

/**
 * Controller for handling newsletter email-related operations.
 */
@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "http://localhost:3000")
public class CombinedEmailController {

    private final CombinedEmailService combinedEmailService;

    /**
     * Constructor for CombinedEmailController.
     *
     * @param combinedEmailService the service to handle newsletter email operations
     */
    @Autowired
    public CombinedEmailController(CombinedEmailService combinedEmailService) {
        this.combinedEmailService = combinedEmailService;
    }

    /**
     * Endpoint to get all emails for the newsletter.
     *
     * @return a list of email addresses for the newsletter
     */
    @GetMapping("/newsletter")
    public List<String> getAllEmailsForNewsletter() {
        return combinedEmailService.getAllEmailsForNewsletter();
    }

    /**
     * Endpoint to download a file containing newsletter emails.
     *
     * @param requestBody a map containing the format of the file (e.g., "csv" or "txt")
     * @return a ResponseEntity containing the file
     * @throws IOException if an I/O error occurs
     */
    @PostMapping("/newsletter/file")
    public ResponseEntity<InputStreamResource> downloadNewsletterEmailsFile(@RequestBody @Schema(example = "{\"format\": \"txt\"}") Map<String, String> requestBody) throws IOException {
        String format = requestBody.get("format");
        String file = combinedEmailService.generateFileForNewsletterEmails(format);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file)
                .contentType(MediaType.parseMediaType("application/" + format))
                .body(resource);
    }
}