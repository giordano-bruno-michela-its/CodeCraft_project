package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ActivityTypeDTO {
    @JsonView({View.GetView.class, View.PostView.class})
    private Long id;

    @JsonView({View.GetView.class, View.SubView.class})
    private String name;

    @JsonView({View.GetView.class, View.SubView.class})
    private String description;
}