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

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminEmailsController {

    @Autowired
    private AdminEmailsService adminEmailsService;

    //Get method to request all the AdminEmails data
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<AdminEmailsDTO> getAllAdminEmails() {
        return  adminEmailsService.getAdminEmails().stream().map(adminEmailsService::convertToDto).toList();
    }

    //Put method to update the AdminEmails data
    @PutMapping("/update/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<AdminEmailsDTO> updateAdminEmails(@PathVariable Long id, @RequestBody AdminEmailsDTO adminEmails) {
        AdminEmails updatedAdminEmails = adminEmailsService.updateAdminEmails(id, adminEmails);
        if(updatedAdminEmails != null) {
            return  ResponseEntity.ok((AdminEmailsDTO)adminEmailsService.convertToDto(updatedAdminEmails));
        }
        return ResponseEntity.notFound().build();
    }
}
