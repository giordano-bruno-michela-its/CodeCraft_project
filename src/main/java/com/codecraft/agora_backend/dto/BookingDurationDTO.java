package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookingDurationDTO {
    @JsonView({View.GetView.class, View.PostView.class})
    @Schema(defaultValue = "1")
    private Long id;

    @JsonView({View.GetView.class, View.SubView.class})
    private String name;

    @JsonView({View.GetView.class, View.SubView.class})
    private String description;
}