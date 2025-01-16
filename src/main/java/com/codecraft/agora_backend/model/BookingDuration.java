package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @JsonView({View.GetView.class, View.PostView.class})
    @Schema(defaultValue = "1")
    private Long id;

    @JsonView({View.GetView.class, View.SubView.class})
    private String name;
    
    @JsonView({View.GetView.class, View.SubView.class})
    private String description;
}
