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
    @JsonView({View.GetView.class, View.PostView.class})
    private Date beginTime;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private Date endTime;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private int participantsQuantity;
    
    @JsonView({View.GetView.class, View.PostView.class})    
    private int guidesQuantity;
    
    @ManyToOne
    @JoinColumn(name = "booking_duration_id")
    @JsonView({View.GetView.class, View.PostView.class})
    private BookingDuration bookingDuration;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @JsonView({View.GetView.class, View.PostView.class})
    private FormType formType = FormType.FORM_BOOKING;
}
