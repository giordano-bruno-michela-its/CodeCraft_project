package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.AgeGroupDTO;
import com.codecraft.agora_backend.model.AgeGroup;
import com.codecraft.agora_backend.repository.AgeGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing age groups.
 */
@Service
public class AgeGroupService {

    private final AgeGroupRepository ageGroupRepository;

    /**
     * Constructor for AgeGroupService.
     *
     * @param ageGroupRepository the repository for age groups
     */
    public AgeGroupService(AgeGroupRepository ageGroupRepository) {
        this.ageGroupRepository = ageGroupRepository;
    }

    /**
     * Get all age groups.
     *
     * @return the list of all age groups
     */
    public List<AgeGroupDTO> getAllAgeGroup() {
        return ageGroupRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Get age group by ID.
     *
     * @param id the age group ID
     * @return the age group with the given ID
     */
    public Optional<AgeGroupDTO> getAgeGroupById(Long id) {
        return ageGroupRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Create a new age group.
     *
     * @param ageGroupDTO the age group data to create
     * @return the created age group
     */
    public AgeGroupDTO createAgeGroup(AgeGroupDTO ageGroupDTO) {
        AgeGroup ageGroup = convertToEntity(ageGroupDTO);
        return convertToDTO(ageGroupRepository.save(ageGroup));
    }

    /**
     * Update an existing age group.
     *
     * @param id          the age group ID
     * @param ageGroupDTO the age group data to update
     * @return the updated age group
     */
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

    /**
     * Delete age group by ID.
     *
     * @param id the age group ID
     */
    public void deleteAgeGroup(Long id) {
        ageGroupRepository.deleteById(id);
    }

    /**
     * Convert an AgeGroup entity to an AgeGroupDTO.
     *
     * @param ageGroup the age group entity
     * @return the age group data transfer object
     */
    private AgeGroupDTO convertToDTO(AgeGroup ageGroup) {
        AgeGroupDTO ageGroupDTO = new AgeGroupDTO();
        ageGroupDTO.setId(ageGroup.getId());
        ageGroupDTO.setName(ageGroup.getName());
        ageGroupDTO.setDescription(ageGroup.getDescription());
        ageGroupDTO.setMinAge(ageGroup.getMinAge());
        ageGroupDTO.setMaxAge(ageGroup.getMaxAge());
        return ageGroupDTO;
    }

    /**
     * Convert an AgeGroupDTO to an AgeGroup entity.
     *
     * @param ageGroupDTO the age group data transfer object
     * @return the age group entity
     */
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