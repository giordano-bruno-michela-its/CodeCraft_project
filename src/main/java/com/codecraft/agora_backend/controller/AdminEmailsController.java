package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.AdminEmailsDTO;
import com.codecraft.agora_backend.model.AdminEmails;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.AdminEmailsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminEmailsController {

    private AdminEmailsService adminEmailsService;

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<AdminEmailsDTO> getAllAdminEmails() {
        return  adminEmailsService.getAdminEmails().stream().map(adminEmailsService::convertToDto).toList();
    }

}
