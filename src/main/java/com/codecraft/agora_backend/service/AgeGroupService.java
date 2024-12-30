package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.AgeGroupDTO;
import com.codecraft.agora_backend.model.AgeGroup;
import com.codecraft.agora_backend.repository.AgeGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgeGroupService {

    private final AgeGroupRepository ageGroupRepository;

    public AgeGroupService(AgeGroupRepository ageGroupRepository) {
        this.ageGroupRepository = ageGroupRepository;
    }

    public List<AgeGroupDTO> getAllAgeGroup() {
        return ageGroupRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<AgeGroupDTO> getAgeGroupById(Long id) {
        return ageGroupRepository.findById(id).map(this::convertToDTO);
    }

    public AgeGroupDTO createAgeGroup(AgeGroupDTO ageGroupDTO) {
        AgeGroup ageGroup = convertToEntity(ageGroupDTO);
        return convertToDTO(ageGroupRepository.save(ageGroup));
    }

    public AgeGroupDTO updateAgeGroup(Long id, AgeGroupDTO ageGroupDTO) {
        Optional<AgeGroup> optionalAgeGroup = ageGroupRepository.findById(id);
        if (optionalAgeGroup.isPresent()) {
            AgeGroup existingAgeGroup = optionalAgeGroup.get();

            if (ageGroupDTO.getName() != null) {
                existingAgeGroup.setName(ageGroupDTO.getName());
            }
            if (ageGroupDTO.getDescription() != null) {
                existingAgeGroup.setDescription(ageGroupDTO.getDescription());
            }
            if (ageGroupDTO.getMinAge() != 0) {
                existingAgeGroup.setMinAge(ageGroupDTO.getMinAge());
            }
            if (ageGroupDTO.getMaxAge() != 0) {
                existingAgeGroup.setMaxAge(ageGroupDTO.getMaxAge());
            }

            return convertToDTO(ageGroupRepository.save(existingAgeGroup));
        }
        return null;
    }

    public void deleteAgeGroup(Long id) {
        ageGroupRepository.deleteById(id);
    }

    private AgeGroupDTO convertToDTO(AgeGroup ageGroup) {
        AgeGroupDTO ageGroupDTO = new AgeGroupDTO();
        ageGroupDTO.setId(ageGroup.getId());
        ageGroupDTO.setName(ageGroup.getName());
        ageGroupDTO.setDescription(ageGroup.getDescription());
        ageGroupDTO.setMinAge(ageGroup.getMinAge());
        ageGroupDTO.setMaxAge(ageGroup.getMaxAge());
        return ageGroupDTO;
    }

    private AgeGroup convertToEntity(AgeGroupDTO ageGroupDTO) {
        AgeGroup ageGroup = new AgeGroup();
        ageGroup.setId(ageGroupDTO.getId());
        ageGroup.setName(ageGroupDTO.getName());
        ageGroup.setDescription(ageGroupDTO.getDescription());
        ageGroup.setMinAge(ageGroupDTO.getMinAge());
        ageGroup.setMaxAge(ageGroupDTO.getMaxAge());
        return ageGroup;
    }
}