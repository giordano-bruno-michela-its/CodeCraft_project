package com.codecraft.agora_backend.repository;

import com.codecraft.agora_backend.model.BookingDuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDurationRepository extends JpaRepository<BookingDuration, Long> {
}
