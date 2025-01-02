package com.codecraft.agora_backend.repository;

import com.codecraft.agora_backend.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
}