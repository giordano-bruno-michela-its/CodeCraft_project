package com.codecraft.agora_backend.model;

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
    
    private Date dataInizio;
    private Date dataFine;
    private int numPartecipanti;
    private int numInsegnanti;

    @ManyToOne
    @JoinColumn(name = "tipo_attivita_id")
    private TipoAttivita tipoAttivita;
}
