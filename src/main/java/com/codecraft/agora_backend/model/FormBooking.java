package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
public class FormBooking extends FormInfo {
    private Date beginTime;
    private Date endTime;
    private int participantsQuantity;
    private int guidesQuantity;
    
    @ManyToOne
    @JoinColumn(name = "booking_duration_id")
    private BookingDuration bookingDuration;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FormType formType = FormType.FORM_BOOKING;
}
