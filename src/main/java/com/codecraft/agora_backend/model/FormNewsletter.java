package com.codecraft.agora_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
public class FormNewsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    private String name;
    private String surname;
    private Date contactDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private NewsletterCheck newsletterCheck = NewsletterCheck.YES;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FormType formType = FormType.FORM_NEWSLETTER;
}
