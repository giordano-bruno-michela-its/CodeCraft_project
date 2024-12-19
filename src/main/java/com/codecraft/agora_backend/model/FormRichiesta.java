package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FormRichiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "fascia_eta_id")
    @JsonView({View.GetView.class, View.PostView.class})
    private FasciaEta fasciaEta;

    @Enumerated(EnumType.STRING)
    @JsonView({View.GetView.class, View.PostView.class})
    private TipoRichiesta tipoRichiesta;
}
