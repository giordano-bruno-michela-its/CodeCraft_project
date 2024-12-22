package com.codecraft.agora_backend.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "form_prenotazione_tipo_attivita",
            joinColumns = @JoinColumn(name = "form_prenotazione_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_attivita_id")
    )
    @JsonView({View.GetView.class, View.PostView.class})
    private Set<TipoAttivita> tipoAttivita;
}
