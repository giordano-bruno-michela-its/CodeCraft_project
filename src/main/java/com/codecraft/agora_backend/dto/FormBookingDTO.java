package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.BookingDuration;
import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class FormBookingDTO extends FormInfoDTO {
    @JsonView({View.GetView.class, View.PostView.class})
    private Date beginTime;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private Date endTime;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private int participantsQuantity;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private int guidesQuantity;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private BookingDuration bookingDuration;
}