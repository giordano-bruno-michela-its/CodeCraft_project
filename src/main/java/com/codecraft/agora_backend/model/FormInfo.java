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
    @JsonView(View.GetView.class)
    private Long id;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String email;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String name;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String surname;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String association;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String phoneNumber;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private Date contactDate;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String additionalInfo;

    @Enumerated(EnumType.STRING)
    @JsonView({View.GetView.class, View.PostView.class})
    private NewsletterCheck newsletterCheck;

    @ManyToOne
    @JoinColumn(name = "age_group_id")
    @JsonView({View.GetView.class, View.PostView.class})
    private AgeGroup ageGroup;

    @ManyToMany
    @JoinTable(
            name = "form_info_activity_type",
            joinColumns = @JoinColumn(name = "form_info_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_type_id")
    )
    @JsonView({View.GetView.class, View.PostView.class})
    private Set<ActivityType> activityType;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @JsonView({View.GetView.class, View.PostView.class})
    private FormType formType = FormType.FORM_INFO;
}
