package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.TipoAttivitaDTO;
import com.codecraft.agora_backend.model.TipoAttivita;
import com.codecraft.agora_backend.repository.TipoAttivitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoAttivitaService {

    private final TipoAttivitaRepository tipoAttivitaRepository;

    public TipoAttivitaService(TipoAttivitaRepository tipoAttivitaRepository) {
        this.tipoAttivitaRepository = tipoAttivitaRepository;
    }

    public List<TipoAttivitaDTO> getAllTipoAttivita() {
        return tipoAttivitaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TipoAttivitaDTO> getTipoAttivitaById(Long id) {
        return tipoAttivitaRepository.findById(id).map(this::convertToDTO);
    }

    public TipoAttivitaDTO createTipoAttivita(TipoAttivitaDTO tipoAttivitaDTO) {
        TipoAttivita tipoAttivita = convertToEntity(tipoAttivitaDTO);
        return convertToDTO(tipoAttivitaRepository.save(tipoAttivita));
    }

    public TipoAttivitaDTO updateTipoAttivita(Long id, TipoAttivitaDTO tipoAttivitaDTO) {
        Optional<TipoAttivita> optionalTipoAttivita = tipoAttivitaRepository.findById(id);
        if (optionalTipoAttivita.isPresent()) {
            TipoAttivita existingTipoAttivita = optionalTipoAttivita.get();

            if (tipoAttivitaDTO.getDenominazione() != null) {
                existingTipoAttivita.setDenominazione(tipoAttivitaDTO.getDenominazione());
            }
            if (tipoAttivitaDTO.getDescrizione() != null) {
                existingTipoAttivita.setDescrizione(tipoAttivitaDTO.getDescrizione());
            }

            return convertToDTO(tipoAttivitaRepository.save(existingTipoAttivita));
        }
        return null;
    }

    public void deleteTipoAttivita(Long id) {
        tipoAttivitaRepository.deleteById(id);
    }

    private TipoAttivitaDTO convertToDTO(TipoAttivita tipoAttivita) {
        TipoAttivitaDTO tipoAttivitaDTO = new TipoAttivitaDTO();
        tipoAttivitaDTO.setId(tipoAttivita.getId());
        tipoAttivitaDTO.setDenominazione(tipoAttivita.getDenominazione());
        tipoAttivitaDTO.setDescrizione(tipoAttivita.getDescrizione());
        return tipoAttivitaDTO;
    }

    private TipoAttivita convertToEntity(TipoAttivitaDTO tipoAttivitaDTO) {
        TipoAttivita tipoAttivita = new TipoAttivita();
        tipoAttivita.setId(tipoAttivitaDTO.getId());
        tipoAttivita.setDenominazione(tipoAttivitaDTO.getDenominazione());
        tipoAttivita.setDescrizione(tipoAttivitaDTO.getDescrizione());
        return tipoAttivita;
    }
}