package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.model.FasciaEta;
import com.codecraft.agora_backend.repository.FasciaEtaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FasciaEtaService {

    private final FasciaEtaRepository fasciaEtaRepository;

    public FasciaEtaService(FasciaEtaRepository fasciaEtaRepository) {
        this.fasciaEtaRepository = fasciaEtaRepository;
    }

    public List<FasciaEta> getAllFasciaEta() {
        return fasciaEtaRepository.findAll();
    }

    public Optional<FasciaEta> getFasciaEtaById(Long id) {
        return fasciaEtaRepository.findById(id);
    }

    public FasciaEta createFasciaEta(FasciaEta fasciaEta) {
        return fasciaEtaRepository.save(fasciaEta);
    }

    public FasciaEta updateFasciaEta(Long id, FasciaEta fasciaEta) {
        Optional<FasciaEta> optionalFasciaEta = fasciaEtaRepository.findById(id);
        if (optionalFasciaEta.isPresent()) {
            FasciaEta existingFasciaEta = optionalFasciaEta.get();

            if (fasciaEta.getDenominazione() != null) {
                existingFasciaEta.setDenominazione(fasciaEta.getDenominazione());
            }
            if (fasciaEta.getDescrizione() != null) {
                existingFasciaEta.setDescrizione(fasciaEta.getDescrizione());
            }
            if (fasciaEta.getEtaMin() != 0) {
                existingFasciaEta.setEtaMin(fasciaEta.getEtaMin());
            }
            if (fasciaEta.getEtaMax() != 0) {
                existingFasciaEta.setEtaMax(fasciaEta.getEtaMax());
            }
            
            return fasciaEtaRepository.save(existingFasciaEta);
        }
        return null;
    }

    public void deleteFasciaEta(Long id) {
        fasciaEtaRepository.deleteById(id);
    }
}