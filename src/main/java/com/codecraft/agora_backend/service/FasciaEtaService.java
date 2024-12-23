package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.FasciaEtaDTO;
import com.codecraft.agora_backend.model.FasciaEta;
import com.codecraft.agora_backend.repository.FasciaEtaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FasciaEtaService {

    private final FasciaEtaRepository fasciaEtaRepository;

    public FasciaEtaService(FasciaEtaRepository fasciaEtaRepository) {
        this.fasciaEtaRepository = fasciaEtaRepository;
    }

    public List<FasciaEtaDTO> getAllFasciaEta() {
        return fasciaEtaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<FasciaEtaDTO> getFasciaEtaById(Long id) {
        return fasciaEtaRepository.findById(id).map(this::convertToDTO);
    }

    public FasciaEtaDTO createFasciaEta(FasciaEtaDTO fasciaEtaDTO) {
        FasciaEta fasciaEta = convertToEntity(fasciaEtaDTO);
        return convertToDTO(fasciaEtaRepository.save(fasciaEta));
    }

    public FasciaEtaDTO updateFasciaEta(Long id, FasciaEtaDTO fasciaEtaDTO) {
        Optional<FasciaEta> optionalFasciaEta = fasciaEtaRepository.findById(id);
        if (optionalFasciaEta.isPresent()) {
            FasciaEta existingFasciaEta = optionalFasciaEta.get();

            if (fasciaEtaDTO.getDenominazione() != null) {
                existingFasciaEta.setDenominazione(fasciaEtaDTO.getDenominazione());
            }
            if (fasciaEtaDTO.getDescrizione() != null) {
                existingFasciaEta.setDescrizione(fasciaEtaDTO.getDescrizione());
            }
            if (fasciaEtaDTO.getEtaMin() != 0) {
                existingFasciaEta.setEtaMin(fasciaEtaDTO.getEtaMin());
            }
            if (fasciaEtaDTO.getEtaMax() != 0) {
                existingFasciaEta.setEtaMax(fasciaEtaDTO.getEtaMax());
            }

            return convertToDTO(fasciaEtaRepository.save(existingFasciaEta));
        }
        return null;
    }

    public void deleteFasciaEta(Long id) {
        fasciaEtaRepository.deleteById(id);
    }

    private FasciaEtaDTO convertToDTO(FasciaEta fasciaEta) {
        FasciaEtaDTO fasciaEtaDTO = new FasciaEtaDTO();
        fasciaEtaDTO.setId(fasciaEta.getId());
        fasciaEtaDTO.setDenominazione(fasciaEta.getDenominazione());
        fasciaEtaDTO.setDescrizione(fasciaEta.getDescrizione());
        fasciaEtaDTO.setEtaMin(fasciaEta.getEtaMin());
        fasciaEtaDTO.setEtaMax(fasciaEta.getEtaMax());
        return fasciaEtaDTO;
    }

    private FasciaEta convertToEntity(FasciaEtaDTO fasciaEtaDTO) {
        FasciaEta fasciaEta = new FasciaEta();
        fasciaEta.setId(fasciaEtaDTO.getId());
        fasciaEta.setDenominazione(fasciaEtaDTO.getDenominazione());
        fasciaEta.setDescrizione(fasciaEtaDTO.getDescrizione());
        fasciaEta.setEtaMin(fasciaEtaDTO.getEtaMin());
        fasciaEta.setEtaMax(fasciaEtaDTO.getEtaMax());
        return fasciaEta;
    }
}