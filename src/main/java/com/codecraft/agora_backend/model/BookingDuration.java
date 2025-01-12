package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class BookingDuration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @JsonView({View.GetView.class})
    private Long id;

    @JsonView({View.GetView.class, View.PostView.class})
    private String name;

    @JsonView({View.GetView.class, View.PostView.class})
    private String description;
}
