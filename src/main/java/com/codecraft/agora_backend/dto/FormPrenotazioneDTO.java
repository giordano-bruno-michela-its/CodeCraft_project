package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.TipoAttivita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class FormPrenotazioneDTO extends FormRichiestaDTO {
    private Date dataInizio;
    private Date dataFine;
    private int numPartecipanti;
    private int numInsegnanti;
    private TipoAttivita tipoAttivita;
}