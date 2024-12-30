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

    public AdminEmails createAdminEmails(AdminEmailsDTO adminEmailsDTO) {
        AdminEmails adminEmails = convertToEntity(adminEmailsDTO);
        return adminEmailsRepository.save(adminEmails);
    }

    public List<AdminEmails> getAdminEmails() {
        return adminEmailsRepository.findAll();
    }

    public Optional<AdminEmails> getAdminEmailsById(int id) {
        return adminEmailsRepository.findById(id);
    }

    public AdminEmails updateAdminEmails(AdminEmails adminEmails) {
        return adminEmailsRepository.save(adminEmails);
    }

    public void deleteAdminEmails(AdminEmails adminEmails) {
        adminEmailsRepository.delete(adminEmails);
    }

    public AdminEmailsDTO convertToDto(AdminEmails adminEmails) {
        AdminEmailsDTO adminEmailsDTO = new AdminEmailsDTO();
        adminEmailsDTO.setId(adminEmails.getId());
        adminEmailsDTO.setAdminEmail(adminEmails.getAdminEmail());
        adminEmailsDTO.setNoReplyEmail(adminEmails.getNoReplyEmail());
        return adminEmailsDTO;
    }

    public AdminEmails convertToEntity(AdminEmailsDTO adminEmailsDTO) {
        AdminEmails adminEmails = new AdminEmails();
        adminEmails.setId(adminEmailsDTO.getId());
        adminEmails.setAdminEmail(adminEmailsDTO.getAdminEmail());
        adminEmails.setNoReplyEmail(adminEmailsDTO.getNoReplyEmail());
        return adminEmails;
    }
}
