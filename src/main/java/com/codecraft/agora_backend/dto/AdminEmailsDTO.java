package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminEmailsDTO {

    @JsonView(View.GetView.class)
    private Long id;

    @JsonView({View.GetView.class, View.SubView.class})
    private String noReplyEmail;

    @JsonView({View.GetView.class, View.SubView.class})
    private String noReplyPassword;

    @JsonView({View.GetView.class, View.SubView.class})
    private String adminEmail;
}
