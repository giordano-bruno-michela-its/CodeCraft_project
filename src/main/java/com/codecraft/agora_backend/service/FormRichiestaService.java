package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.FormRichiestaDTO;
import com.codecraft.agora_backend.model.FormRichiesta;
import com.codecraft.agora_backend.repository.FormRichiestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormRichiestaService {

    @Autowired
    private FormRichiestaRepository formRichiestaRepository;

    public List<FormRichiesta> getAllFormRichieste() {
        return formRichiestaRepository.findAll();
    }

    public Optional<FormRichiesta> getFormRichiestaById(Long id) {
        return formRichiestaRepository.findById(id);
    }

    public FormRichiesta createFormRichiesta(FormRichiestaDTO formRichiestaDTO) {
        FormRichiesta formRichiesta = new FormRichiesta();

        formRichiesta.setEmail(formRichiestaDTO.getEmail());
        formRichiesta.setNome(formRichiestaDTO.getNome());
        formRichiesta.setCognome(formRichiestaDTO.getCognome());
        formRichiesta.setEnte(formRichiestaDTO.getEnte());
        formRichiesta.setTelefono(formRichiestaDTO.getTelefono());
        formRichiesta.setDataContatto(formRichiestaDTO.getDataContatto());
        formRichiesta.setDescrizione(formRichiestaDTO.getDescrizione());
        formRichiesta.setFasciaEta(formRichiestaDTO.getFasciaEta());
        formRichiesta.setTipoRichiesta(formRichiestaDTO.getTipoRichiesta());
        return formRichiestaRepository.save(formRichiesta);
    }

    public FormRichiesta updateFormRichiesta(Long id, FormRichiestaDTO formRichiestaDTO) {
        Optional<FormRichiesta> optionalFormRichiesta = formRichiestaRepository.findById(id);
        if (optionalFormRichiesta.isPresent()) {
            FormRichiesta formRichiesta = optionalFormRichiesta.get();

            formRichiesta.setEmail(formRichiestaDTO.getEmail());
            formRichiesta.setNome(formRichiestaDTO.getNome());
            formRichiesta.setCognome(formRichiestaDTO.getCognome());
            formRichiesta.setEnte(formRichiestaDTO.getEnte());
            formRichiesta.setTelefono(formRichiestaDTO.getTelefono());
            formRichiesta.setDataContatto(formRichiestaDTO.getDataContatto());
            formRichiesta.setDescrizione(formRichiestaDTO.getDescrizione());
            formRichiesta.setFasciaEta(formRichiestaDTO.getFasciaEta());
            formRichiesta.setTipoRichiesta(formRichiestaDTO.getTipoRichiesta());
            return formRichiestaRepository.save(formRichiesta);
        }
        return null;
    }

    public void deleteFormRichiesta(Long id) {
        formRichiestaRepository.deleteById(id);
    }
}