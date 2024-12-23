package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.FasciaEta;
import com.codecraft.agora_backend.model.TipoRichiesta;
import com.codecraft.agora_backend.model.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class FormRichiestaDTO {
    @JsonView(View.GetView.class)
    private Long id;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String email;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String nome;
    
    @JsonView({View.GetView.class, View.PostView.class})    
    private String cognome;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String ente;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String telefono;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private Date dataContatto;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private String descrizione;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private FasciaEta fasciaEta;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private TipoRichiesta tipoRichiesta;
}