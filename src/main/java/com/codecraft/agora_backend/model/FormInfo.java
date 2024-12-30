package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @ManyToOne
    @JoinColumn(name = "age_group_id")
    @JsonView({View.GetView.class, View.PostView.class})
    private AgeGroup ageGroup;

    @Enumerated(EnumType.STRING)
    @JsonView({View.GetView.class, View.PostView.class})
    private FormType formType;
}
