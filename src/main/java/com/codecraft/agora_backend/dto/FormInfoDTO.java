package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.AgeGroup;
import com.codecraft.agora_backend.model.FormType;
import com.codecraft.agora_backend.model.NewsletterCheck;
import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class FormInfoDTO {
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

    @JsonView({View.GetView.class, View.PostView.class})
    private NewsletterCheck newsletterCheck;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private AgeGroup ageGroup;

    @JsonView({View.GetView.class, View.PostView.class})
    private Set<ActivityTypeDTO> activityType;

    @JsonView({View.GetView.class, View.PostView.class})
    private String uniqueCode;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private FormType formType;
}