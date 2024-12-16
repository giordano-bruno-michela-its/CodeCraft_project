package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.TipoAttivita;
import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class FormPrenotazioneDTO extends FormRichiestaDTO {
    @JsonView({View.GetView.class, View.PostView.class})
    private Date dataInizio;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private Date dataFine;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private int numPartecipanti;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private int numInsegnanti;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private TipoAttivita tipoAttivita;
}