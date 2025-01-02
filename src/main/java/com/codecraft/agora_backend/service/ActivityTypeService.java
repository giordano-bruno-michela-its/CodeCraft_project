package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.ActivityTypeDTO;
import com.codecraft.agora_backend.model.ActivityType;
import com.codecraft.agora_backend.repository.ActivityTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityTypeService {

    private final ActivityTypeRepository activityTypeRepository;

    public ActivityTypeService(ActivityTypeRepository activityTypeRepository) {
        this.activityTypeRepository = activityTypeRepository;
    }

    public List<ActivityTypeDTO> getAllActivityType() {
        return activityTypeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ActivityTypeDTO> getActivityTypeById(Long id) {
        return activityTypeRepository.findById(id).map(this::convertToDTO);
    }

    public ActivityTypeDTO createActivityType(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = convertToEntity(activityTypeDTO);
        return convertToDTO(activityTypeRepository.save(activityType));
    }

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

    public void deleteActivityType(Long id) {
        activityTypeRepository.deleteById(id);
    }

    private ActivityTypeDTO convertToDTO(ActivityType activityType) {
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO();
        activityTypeDTO.setId(activityType.getId());
        activityTypeDTO.setName(activityType.getName());
        activityTypeDTO.setDescription(activityType.getDescription());
        return activityTypeDTO;
    }

    private ActivityType convertToEntity(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = new ActivityType();
        activityType.setId(activityTypeDTO.getId());
        activityType.setName(activityTypeDTO.getName());
        activityType.setDescription(activityTypeDTO.getDescription());
        return activityType;
    }
}