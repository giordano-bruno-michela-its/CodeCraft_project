package com.codecraft.agora_backend.repository;

import com.codecraft.agora_backend.model.FormInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormInfoRepository extends JpaRepository<FormInfo, Long> {
    Optional<FormInfo> findByEmailAndUniqueCode(String email, String uniqueCode);
    boolean existsByUniqueCode(String uniqueCode);
}
