package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.AdminEmailsDTO;
import com.codecraft.agora_backend.model.AdminEmails;
import com.codecraft.agora_backend.repository.AdminEmailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminEmailsService {

    @Autowired
    private AdminEmailsRepository adminEmailsRepository;

    public List<AdminEmails> getAdminEmails() {
        return adminEmailsRepository.findAll();
    }

    //Get admin emails with specific id
    public Optional<AdminEmails> getAdminEmailsById(Long id) {
        return adminEmailsRepository.findById(id);
    }

    //Update adminEmails with id, and new data, calls method to update credentials in SendEmailService
    public AdminEmails updateAdminEmails(Long id, AdminEmailsDTO adminEmailsDTO) {
        Optional<AdminEmails> optionalAdminEmails = adminEmailsRepository.findById(id);
        if(optionalAdminEmails.isPresent()) {
            AdminEmails adminEmails = (AdminEmails) optionalAdminEmails.get();
            if(adminEmailsDTO.getNoReplyEmail() != null) {
                adminEmails.setNoReplyEmail(adminEmailsDTO.getNoReplyEmail());
            }
            if (adminEmailsDTO.getNoReplyPassword() != null) {
                adminEmails.setNoReplyPassword(adminEmailsDTO.getNoReplyPassword());
            }
            if (adminEmailsDTO.getAdminEmail() != null) {
                adminEmails.setAdminEmail(adminEmailsDTO.getAdminEmail());
            }
            return adminEmailsRepository.save(adminEmails);
        }
        return null;
    }

    //Converts to dto
    public AdminEmailsDTO convertToDto(AdminEmails adminEmails) {
        AdminEmailsDTO adminEmailsDTO = new AdminEmailsDTO();
        adminEmailsDTO.setId(adminEmails.getId());
        adminEmailsDTO.setAdminEmail(adminEmails.getAdminEmail());
        adminEmailsDTO.setNoReplyEmail(adminEmails.getNoReplyEmail());
        adminEmailsDTO.setNoReplyPassword(adminEmails.getNoReplyPassword());
        return adminEmailsDTO;
    }

    //Converts to entity
    public AdminEmails convertToEntity(AdminEmailsDTO adminEmailsDTO) {
        AdminEmails adminEmails = new AdminEmails();
        adminEmails.setId(adminEmailsDTO.getId());
        adminEmails.setAdminEmail(adminEmailsDTO.getAdminEmail());
        adminEmails.setNoReplyEmail(adminEmailsDTO.getNoReplyEmail());
        adminEmails.setNoReplyPassword(adminEmailsDTO.getNoReplyPassword());
        return adminEmails;
    }
}
