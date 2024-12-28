package com.codecraft.agora_backend.repository;

import com.codecraft.agora_backend.model.AdminEmails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminEmailsRepository extends JpaRepository<AdminEmails, Integer> {
}
