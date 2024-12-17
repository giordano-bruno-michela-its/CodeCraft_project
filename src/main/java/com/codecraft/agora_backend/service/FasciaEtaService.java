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
            existingFasciaEta.setDenominazione(fasciaEta.getDenominazione());
            existingFasciaEta.setDescrizione(fasciaEta.getDescrizione());
            existingFasciaEta.setEtaMin(fasciaEta.getEtaMin());
            existingFasciaEta.setEtaMax(fasciaEta.getEtaMax());
            return fasciaEtaRepository.save(existingFasciaEta);
        }
        return null;
    }

    public void deleteFasciaEta(Long id) {
        fasciaEtaRepository.deleteById(id);
    }
}