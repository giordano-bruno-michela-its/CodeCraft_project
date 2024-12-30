package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AgeGroupDTO {
    @JsonView(View.GetView.class)
    private Long id;

    @JsonView({View.GetView.class, View.PostView.class})
    private String name;

    @JsonView({View.GetView.class, View.PostView.class})
    private String description;

    @JsonView({View.GetView.class, View.PostView.class})
    private int minAge;

    @JsonView({View.GetView.class, View.PostView.class})
    private int maxAge;
}