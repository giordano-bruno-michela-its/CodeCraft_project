package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.ActivityTypeDTO;
import com.codecraft.agora_backend.model.ActivityType;
import com.codecraft.agora_backend.repository.ActivityTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing activity types.
 */
@Service
public class ActivityTypeService {

    private final ActivityTypeRepository activityTypeRepository;

    /**
     * Constructor for ActivityTypeService.
     *
     * @param activityTypeRepository the repository for activity types
     */
    public ActivityTypeService(ActivityTypeRepository activityTypeRepository) {
        this.activityTypeRepository = activityTypeRepository;
    }

    /**
     * Get all activity types.
     *
     * @return the list of all activity types
     */
    public List<ActivityTypeDTO> getAllActivityType() {
        return activityTypeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Get activity type by ID.
     *
     * @param id the activity type ID
     * @return the activity type with the given ID
     */
    public Optional<ActivityTypeDTO> getActivityTypeById(Long id) {
        return activityTypeRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Create a new activity type.
     *
     * @param activityTypeDTO the activity type data to create
     * @return the created activity type
     */
    public ActivityTypeDTO createActivityType(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = convertToEntity(activityTypeDTO);
        return convertToDTO(activityTypeRepository.save(activityType));
    }

    /**
     * Update an existing activity type.
     *
     * @param id the activity type ID
     * @param activityTypeDTO the activity type data to update
     * @return the updated activity type
     */
    public ActivityTypeDTO updateActivityType(Long id, ActivityTypeDTO activityTypeDTO) {
        Optional<ActivityType> optionalActivityType = activityTypeRepository.findById(id);
        if (optionalActivityType.isPresent()) {
            ActivityType existingActivityType = optionalActivityType.get();

            if (activityTypeDTO.getName() != null) {
                existingActivityType.setName(activityTypeDTO.getName());
            }
            if (activityTypeDTO.getDescription() != null) {
                existingActivityType.setDescription(activityTypeDTO.getDescription());
            }

            return convertToDTO(activityTypeRepository.save(existingActivityType));
        }
        return null;
    }

    /**
     * Delete activity type by ID.
     *
     * @param id the activity type ID
     */
    public void deleteActivityType(Long id) {
        activityTypeRepository.deleteById(id);
    }

    /**
     * Convert an ActivityType entity to an ActivityTypeDTO.
     *
     * @param activityType the activity type entity
     * @return the activity type data transfer object
     */
    private ActivityTypeDTO convertToDTO(ActivityType activityType) {
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO();
        activityTypeDTO.setId(activityType.getId());
        activityTypeDTO.setName(activityType.getName());
        activityTypeDTO.setDescription(activityType.getDescription());
        return activityTypeDTO;
    }

    /**
     * Convert an ActivityTypeDTO to an ActivityType entity.
     *
     * @param activityTypeDTO the activity type data transfer object
     * @return the activity type entity
     */
    private ActivityType convertToEntity(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = new ActivityType();
        activityType.setId(activityTypeDTO.getId());
        activityType.setName(activityTypeDTO.getName());
        activityType.setDescription(activityTypeDTO.getDescription());
        return activityType;
    }
}