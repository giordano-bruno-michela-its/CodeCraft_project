package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class FormBooking extends FormInfo {
    @JsonView({View.GetView.class, View.PostView.class})
    private Date beginTime;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private Date endTime;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private int participantsQuantity;
    
    @JsonView({View.GetView.class, View.PostView.class})    
    private int guidesQuantity;

    @ManyToMany
    @JoinTable(
            name = "form_booking_activity_type",
            joinColumns = @JoinColumn(name = "form_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_type_id")
    )
    @JsonView({View.GetView.class, View.PostView.class})
    private Set<ActivityType> activityType;
}
