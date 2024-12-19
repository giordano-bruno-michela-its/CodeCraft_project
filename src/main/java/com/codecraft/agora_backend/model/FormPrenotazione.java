package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class FormPrenotazione extends FormRichiesta {
    @JsonView({View.GetView.class, View.PostView.class})
    private Date dataInizio;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private Date dataFine;
    
    @JsonView({View.GetView.class, View.PostView.class})
    private int numPartecipanti;
    
    @JsonView({View.GetView.class, View.PostView.class})    
    private int numInsegnanti;

    @ManyToOne
    @JoinColumn(name = "tipo_attivita_id")
    @JsonView({View.GetView.class, View.PostView.class})
    private TipoAttivita tipoAttivita;
}
