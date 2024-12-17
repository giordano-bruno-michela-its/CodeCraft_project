package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.model.TipoAttivita;
import com.codecraft.agora_backend.repository.TipoAttivitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoAttivitaService {

    private final TipoAttivitaRepository tipoAttivitaRepository;

    public TipoAttivitaService(TipoAttivitaRepository tipoAttivitaRepository) {
        this.tipoAttivitaRepository = tipoAttivitaRepository;
    }

    public List<TipoAttivita> getAllTipoAttivita() {
        return tipoAttivitaRepository.findAll();
    }

    public Optional<TipoAttivita> getTipoAttivitaById(Long id) {
        return tipoAttivitaRepository.findById(id);
    }

    public TipoAttivita createTipoAttivita(TipoAttivita tipoAttivita) {
        return tipoAttivitaRepository.save(tipoAttivita);
    }

    public TipoAttivita updateTipoAttivita(Long id, TipoAttivita tipoAttivita) {
        Optional<TipoAttivita> optionalTipoAttivita = tipoAttivitaRepository.findById(id);
        if (optionalTipoAttivita.isPresent()) {
            TipoAttivita existingTipoAttivita = optionalTipoAttivita.get();
            existingTipoAttivita.setDenominazione(tipoAttivita.getDenominazione());
            existingTipoAttivita.setDescrizione(tipoAttivita.getDescrizione());
            return tipoAttivitaRepository.save(existingTipoAttivita);
        }
        return null;
    }

    public void deleteTipoAttivita(Long id) {
        tipoAttivitaRepository.deleteById(id);
    }
}