package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    @JsonView(View.GetView.class)
    private Long id;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String name;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String username;
    
    @JsonView({View.GetView.class, View.PostView.class})    
    private String email;
    
    @Schema(defaultValue = "false")
    private boolean deleted;

    @JsonView({View.GetView.class, View.PostView.class})
    private Set<String> roles;
}