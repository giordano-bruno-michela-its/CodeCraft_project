package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.FormPrenotazioneDTO;
import com.codecraft.agora_backend.dto.FormRichiestaDTO;
import com.codecraft.agora_backend.dto.TipoAttivitaDTO;
import com.codecraft.agora_backend.model.FormPrenotazione;
import com.codecraft.agora_backend.model.FormRichiesta;
import com.codecraft.agora_backend.model.TipoAttivita;
import com.codecraft.agora_backend.model.TipoRichiesta;
import com.codecraft.agora_backend.repository.FormRichiestaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormRichiestaService {

    private final FormRichiestaRepository formRichiestaRepository;

    public FormRichiestaService(FormRichiestaRepository formRichiestaRepository) {
        this.formRichiestaRepository = formRichiestaRepository;
    }

    public List<FormRichiesta> getAllFormRichieste() {
        return formRichiestaRepository.findAll();
    }

    public Optional<FormRichiesta> getFormRichiestaById(Long id) {
        return formRichiestaRepository.findById(id);
    }

    public FormRichiesta createFormRichiesta(FormRichiestaDTO formRichiestaDTO) {
        FormRichiesta formRichiesta = convertToEntity(formRichiestaDTO);
        return formRichiestaRepository.save(formRichiesta);
    }

    public FormPrenotazione createFormPrenotazione(FormPrenotazioneDTO formPrenotazioneDTO) {
        FormPrenotazione formPrenotazione = (FormPrenotazione) convertToEntity(formPrenotazioneDTO);
        return formRichiestaRepository.save(formPrenotazione);
    }

    public FormRichiesta updateFormRichiesta(Long id, FormRichiestaDTO formRichiestaDTO) {
        Optional<FormRichiesta> optionalFormRichiesta = formRichiestaRepository.findById(id);
        if (optionalFormRichiesta.isPresent()) {
            FormRichiesta formRichiesta = optionalFormRichiesta.get();
            updateCommonFields(formRichiesta, formRichiestaDTO);
            return formRichiestaRepository.save(formRichiesta);
        }
        return null;
    }

    public FormPrenotazione updateFormPrenotazione(Long id, FormPrenotazioneDTO formPrenotazioneDTO) {
        Optional<FormRichiesta> optionalFormRichiesta = formRichiestaRepository.findById(id);
        if (optionalFormRichiesta.isPresent() && optionalFormRichiesta.get() instanceof FormPrenotazione) {
            FormPrenotazione formPrenotazione = (FormPrenotazione) optionalFormRichiesta.get();
            updateCommonFields(formPrenotazione, formPrenotazioneDTO);
            updateFormPrenotazioneFields(formPrenotazione, formPrenotazioneDTO);
            return formRichiestaRepository.save(formPrenotazione);
        }
        return null;
    }

    private void updateCommonFields(FormRichiesta formRichiesta, FormRichiestaDTO formRichiestaDTO) {
        if (formRichiestaDTO.getEmail() != null) {
            formRichiesta.setEmail(formRichiestaDTO.getEmail());
        }
        if (formRichiestaDTO.getNome() != null) {
            formRichiesta.setNome(formRichiestaDTO.getNome());
        }
        if (formRichiestaDTO.getCognome() != null) {
            formRichiesta.setCognome(formRichiestaDTO.getCognome());
        }
        if (formRichiestaDTO.getEnte() != null) {
            formRichiesta.setEnte(formRichiestaDTO.getEnte());
        }
        if (formRichiestaDTO.getTelefono() != null) {
            formRichiesta.setTelefono(formRichiestaDTO.getTelefono());
        }
        if (formRichiestaDTO.getDataContatto() != null) {
            formRichiesta.setDataContatto(formRichiestaDTO.getDataContatto());
        }
        if (formRichiestaDTO.getDescrizione() != null) {
            formRichiesta.setDescrizione(formRichiestaDTO.getDescrizione());
        }
        if (formRichiestaDTO.getFasciaEta() != null) {
            formRichiesta.setFasciaEta(formRichiestaDTO.getFasciaEta());
        }
        if (formRichiestaDTO.getTipoRichiesta() != null) {
            formRichiesta.setTipoRichiesta(formRichiestaDTO.getTipoRichiesta());
        }
    }

    private void updateFormPrenotazioneFields(FormPrenotazione formPrenotazione, FormPrenotazioneDTO formPrenotazioneDTO) {
        if (formPrenotazioneDTO.getDataInizio() != null) {
            formPrenotazione.setDataInizio(formPrenotazioneDTO.getDataInizio());
        }
        if (formPrenotazioneDTO.getDataFine() != null) {
            formPrenotazione.setDataFine(formPrenotazioneDTO.getDataFine());
        }
        if (formPrenotazioneDTO.getNumPartecipanti() != 0) {
            formPrenotazione.setNumPartecipanti(formPrenotazioneDTO.getNumPartecipanti());
        }
        if (formPrenotazioneDTO.getNumInsegnanti() != 0) {
            formPrenotazione.setNumInsegnanti(formPrenotazioneDTO.getNumInsegnanti());
        }
        if (formPrenotazioneDTO.getTipoAttivita() != null) {
            Set<TipoAttivita> tipoAttivitaSet = formPrenotazioneDTO.getTipoAttivita().stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toSet());
            formPrenotazione.setTipoAttivita(tipoAttivitaSet);
        }
    }

    public void deleteFormRichiesta(Long id) {
        formRichiestaRepository.deleteById(id);
    }

    public FormRichiestaDTO convertToDTO(FormRichiesta formRichiesta) {
        if (formRichiesta instanceof FormPrenotazione) {
            FormPrenotazione formPrenotazione = (FormPrenotazione) formRichiesta;
            FormPrenotazioneDTO formPrenotazioneDTO = new FormPrenotazioneDTO();
            formPrenotazioneDTO.setId(formPrenotazione.getId());
            formPrenotazioneDTO.setEmail(formPrenotazione.getEmail());
            formPrenotazioneDTO.setNome(formPrenotazione.getNome());
            formPrenotazioneDTO.setCognome(formPrenotazione.getCognome());
            formPrenotazioneDTO.setEnte(formPrenotazione.getEnte());
            formPrenotazioneDTO.setTelefono(formPrenotazione.getTelefono());
            formPrenotazioneDTO.setDataContatto(formPrenotazione.getDataContatto());
            formPrenotazioneDTO.setDescrizione(formPrenotazione.getDescrizione());
            formPrenotazioneDTO.setFasciaEta(formPrenotazione.getFasciaEta());
            formPrenotazioneDTO.setTipoRichiesta(formPrenotazione.getTipoRichiesta());
            formPrenotazioneDTO.setDataInizio(formPrenotazione.getDataInizio());
            formPrenotazioneDTO.setDataFine(formPrenotazione.getDataFine());
            formPrenotazioneDTO.setNumPartecipanti(formPrenotazione.getNumPartecipanti());
            formPrenotazioneDTO.setNumInsegnanti(formPrenotazione.getNumInsegnanti());
            formPrenotazioneDTO.setTipoAttivita(formPrenotazione.getTipoAttivita().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toSet()));
            return formPrenotazioneDTO;
        } else {
            FormRichiestaDTO formRichiestaDTO = new FormRichiestaDTO();
            formRichiestaDTO.setId(formRichiesta.getId());
            formRichiestaDTO.setEmail(formRichiesta.getEmail());
            formRichiestaDTO.setNome(formRichiesta.getNome());
            formRichiestaDTO.setCognome(formRichiesta.getCognome());
            formRichiestaDTO.setEnte(formRichiesta.getEnte());
            formRichiestaDTO.setTelefono(formRichiesta.getTelefono());
            formRichiestaDTO.setDataContatto(formRichiesta.getDataContatto());
            formRichiestaDTO.setDescrizione(formRichiesta.getDescrizione());
            formRichiestaDTO.setFasciaEta(formRichiesta.getFasciaEta());
            formRichiestaDTO.setTipoRichiesta(formRichiesta.getTipoRichiesta());
            return formRichiestaDTO;
        }
    }

    private FormRichiesta convertToEntity(FormRichiestaDTO formRichiestaDTO) {
        FormRichiesta formRichiesta;
        if (formRichiestaDTO.getTipoRichiesta() == TipoRichiesta.RICHIESTA_PRENOTAZIONE) {
            formRichiesta = new FormPrenotazione();
        } else {
            formRichiesta = new FormRichiesta();
        }

        formRichiesta.setEmail(formRichiestaDTO.getEmail());
        formRichiesta.setNome(formRichiestaDTO.getNome());
        formRichiesta.setCognome(formRichiestaDTO.getCognome());
        formRichiesta.setEnte(formRichiestaDTO.getEnte());
        formRichiesta.setTelefono(formRichiestaDTO.getTelefono());
        formRichiesta.setDataContatto(formRichiestaDTO.getDataContatto());
        formRichiesta.setDescrizione(formRichiestaDTO.getDescrizione());
        formRichiesta.setFasciaEta(formRichiestaDTO.getFasciaEta());
        formRichiesta.setTipoRichiesta(formRichiestaDTO.getTipoRichiesta());

        if (formRichiesta instanceof FormPrenotazione formPrenotazione) {
            FormPrenotazioneDTO formPrenotazioneDTO = (FormPrenotazioneDTO) formRichiestaDTO;
            formPrenotazione.setDataInizio(formPrenotazioneDTO.getDataInizio());
            formPrenotazione.setDataFine(formPrenotazioneDTO.getDataFine());
            formPrenotazione.setNumPartecipanti(formPrenotazioneDTO.getNumPartecipanti());
            formPrenotazione.setNumInsegnanti(formPrenotazioneDTO.getNumInsegnanti());
            Set<TipoAttivita> tipoAttivitaSet = formPrenotazioneDTO.getTipoAttivita().stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(TipoAttivita::getId))));
            formPrenotazione.setTipoAttivita(tipoAttivitaSet);
        }

        return formRichiesta;
    }
    
    private TipoAttivita convertToEntity(TipoAttivitaDTO tipoAttivitaDTO) {
        TipoAttivita tipoAttivita = new TipoAttivita();
        tipoAttivita.setId(tipoAttivitaDTO.getId());
        tipoAttivita.setDenominazione(tipoAttivitaDTO.getDenominazione());
        tipoAttivita.setDescrizione(tipoAttivitaDTO.getDescrizione());
        return tipoAttivita;
    }
    
    private TipoAttivitaDTO convertToDTO(TipoAttivita tipoAttivita) {
        TipoAttivitaDTO tipoAttivitaDTO = new TipoAttivitaDTO();
        tipoAttivitaDTO.setId(tipoAttivita.getId());
        tipoAttivitaDTO.setDenominazione(tipoAttivita.getDenominazione());
        tipoAttivitaDTO.setDescrizione(tipoAttivita.getDescrizione());
        return tipoAttivitaDTO;
    }
}