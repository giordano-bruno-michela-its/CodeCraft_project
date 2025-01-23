package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.AdminEmailsDTO;
import com.codecraft.agora_backend.model.AdminEmails;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.AdminEmailsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing admin emails.
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminEmailsController {

    @Autowired
    private AdminEmailsService adminEmailsService;

    /**
     * Get all admin emails.
     *
     * @return the list of all admin emails
     */
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<AdminEmailsDTO> getAllAdminEmails() {
        return adminEmailsService.getAdminEmails().stream().map(adminEmailsService::convertToDto).toList();
    }

    /**
     * Update admin emails.
     *
     * @param adminEmails the admin emails data transfer object
     * @return the updated admin emails data transfer object
     */
    @PutMapping("/updateMail")
    @JsonView(View.PostView.class)
    public ResponseEntity<AdminEmailsDTO> updateAdminEmails(@RequestBody AdminEmailsDTO adminEmails) {
        AdminEmails updatedAdminEmails = adminEmailsService.updateAdminEmails(1L, adminEmails);
        if (updatedAdminEmails != null) {
            return ResponseEntity.ok((AdminEmailsDTO) adminEmailsService.convertToDto(updatedAdminEmails));
        }
        return ResponseEntity.notFound().build();
    }
}
