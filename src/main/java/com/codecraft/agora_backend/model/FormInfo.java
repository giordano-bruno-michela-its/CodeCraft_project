package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
public class FormInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String surname;
    private String association;
    private String phoneNumber;
    private Date contactDate;
    private String additionalInfo;

    @Enumerated(EnumType.STRING)
    private NewsletterCheck newsletterCheck;

    @ManyToOne
    @JoinColumn(name = "age_group_id")
    private AgeGroup ageGroup;

    @ManyToMany
    @JoinTable(name = "form_info_activity_type", joinColumns = @JoinColumn(name = "form_info_id"), inverseJoinColumns = @JoinColumn(name = "activity_type_id"))
    private Set<ActivityType> activityType;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FormType formType = FormType.FORM_INFO;
}
