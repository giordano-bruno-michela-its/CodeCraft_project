package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
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
public class AdminEmails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.GetView.class)
    private Long id;

    @JsonView({View.GetView.class, View.PostView.class})
    private String noReplyEmail;

    @JsonView({View.GetView.class, View.PostView.class})
    private String adminEmail;
}
