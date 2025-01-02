package com.codecraft.agora_backend.repository;

import com.codecraft.agora_backend.model.AgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeGroupRepository extends JpaRepository<AgeGroup, Long> {
}