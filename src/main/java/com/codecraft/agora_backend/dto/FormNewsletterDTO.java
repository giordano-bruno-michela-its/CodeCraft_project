package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.FormType;
import com.codecraft.agora_backend.model.NewsletterCheck;
import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class FormNewsletterDTO {
    @JsonView(View.GetView.class)
    private Long id;

    @JsonView({View.GetView.class, View.PostView.class})
    private String email;

    @JsonView({View.GetView.class, View.PostView.class})
    private String name;

    @JsonView({View.GetView.class, View.PostView.class})
    private String surname;

    @JsonView({View.GetView.class, View.PostView.class})
    private Date contactDate;

    @JsonView({View.GetView.class, View.PostView.class})
    private NewsletterCheck newsletterCheck;

    @JsonView({View.GetView.class, View.PostView.class})
    private FormType formType;
}