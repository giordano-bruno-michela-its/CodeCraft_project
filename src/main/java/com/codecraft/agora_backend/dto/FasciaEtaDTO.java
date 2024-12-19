package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FasciaEtaDTO {
    @JsonView(View.GetView.class)
    private Long id;

    @JsonView({View.GetView.class, View.PostView.class})
    private String denominazione;

    @JsonView({View.GetView.class, View.PostView.class})
    private String descrizione;

    @JsonView({View.GetView.class, View.PostView.class})
    private int etaMin;

    @JsonView({View.GetView.class, View.PostView.class})
    private int etaMax;
}