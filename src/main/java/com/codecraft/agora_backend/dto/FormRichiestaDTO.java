package com.codecraft.agora_backend.dto;

import com.codecraft.agora_backend.model.FasciaEta;
import com.codecraft.agora_backend.model.TipoRichiesta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"id"})
public class FormRichiestaDTO {
    private Long id;
    private String email;
    private String nome;
    private String cognome;
    private String ente;
    private String telefono;
    private Date dataContatto;
    private String descrizione;
    private FasciaEta fasciaEta;
    private TipoRichiesta tipoRichiesta;
}