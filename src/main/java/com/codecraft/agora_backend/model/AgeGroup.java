package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class AgeGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.GetView.class, View.PostView.class})
    @Schema(defaultValue = "1")
    private Long id;

    @JsonView({View.GetView.class, View.SubView.class})
    private String name;

    @JsonView({View.GetView.class, View.SubView.class})
    private String description;

    @JsonView({View.GetView.class, View.SubView.class})
    private int minAge;

    @JsonView({View.GetView.class, View.SubView.class})
    private int maxAge;
}
