package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
public class FormNewsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @JsonView({View.GetView.class, View.PostView.class})
    private NewsletterCheck newsletterCheck = NewsletterCheck.YES;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @JsonView({View.GetView.class, View.PostView.class})
    private FormType formType = FormType.FORM_NEWSLETTER;
}
