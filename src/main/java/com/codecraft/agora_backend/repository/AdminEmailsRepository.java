package com.codecraft.agora_backend.repository;

import com.codecraft.agora_backend.model.AdminEmails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminEmailsRepository extends JpaRepository<AdminEmails, Long> {
}
