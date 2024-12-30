package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.FormBookingDTO;
import com.codecraft.agora_backend.dto.FormInfoDTO;
import com.codecraft.agora_backend.dto.ActivityTypeDTO;
import com.codecraft.agora_backend.model.FormBooking;
import com.codecraft.agora_backend.model.FormInfo;
import com.codecraft.agora_backend.model.ActivityType;
import com.codecraft.agora_backend.model.FormType;
import com.codecraft.agora_backend.repository.FormInfoRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormInfoService {

    private final FormInfoRepository formInfoRepository;

    public FormInfoService(FormInfoRepository formInfoRepository) {
        this.formInfoRepository = formInfoRepository;
    }

    public List<FormInfo> getAllFormInfo() {
        return formInfoRepository.findAll();
    }

    public Optional<FormInfo> getFormInfoById(Long id) {
        return formInfoRepository.findById(id);
    }

    public FormInfo createFormInfo(FormInfoDTO formInfoDTO) {
        FormInfo formInfo = convertToEntity(formInfoDTO);
        return formInfoRepository.save(formInfo);
    }

    public FormBooking createFormBooking(FormBookingDTO formBookingDTO) {
        FormBooking formBooking = (FormBooking) convertToEntity(formBookingDTO);
        return formInfoRepository.save(formBooking);
    }

    public FormInfo updateFormInfo(Long id, FormInfoDTO formInfoDTO) {
        Optional<FormInfo> optionalFormInfo = formInfoRepository.findById(id);
        if (optionalFormInfo.isPresent()) {
            FormInfo formInfo = optionalFormInfo.get();
            updateCommonFields(formInfo, formInfoDTO);
            return formInfoRepository.save(formInfo);
        }
        return null;
    }

    public FormBooking updateFormBooking(Long id, FormBookingDTO formBookingDTO) {
        Optional<FormInfo> optionalFormInfo = formInfoRepository.findById(id);
        if (optionalFormInfo.isPresent() && optionalFormInfo.get() instanceof FormBooking) {
            FormBooking formBooking = (FormBooking) optionalFormInfo.get();
            updateCommonFields(formBooking, formBookingDTO);
            updateFormBookingFields(formBooking, formBookingDTO);
            return formInfoRepository.save(formBooking);
        }
        return null;
    }

    private void updateCommonFields(FormInfo formInfo, FormInfoDTO formInfoDTO) {
        if (formInfoDTO.getEmail() != null) {
            formInfo.setEmail(formInfoDTO.getEmail());
        }
        if (formInfoDTO.getName() != null) {
            formInfo.setName(formInfoDTO.getName());
        }
        if (formInfoDTO.getSurname() != null) {
            formInfo.setSurname(formInfoDTO.getSurname());
        }
        if (formInfoDTO.getAssociation() != null) {
            formInfo.setAssociation(formInfoDTO.getAssociation());
        }
        if (formInfoDTO.getPhoneNumber() != null) {
            formInfo.setPhoneNumber(formInfoDTO.getPhoneNumber());
        }
        if (formInfoDTO.getContactDate() != null) {
            formInfo.setContactDate(formInfoDTO.getContactDate());
        }
        if (formInfoDTO.getAdditionalInfo() != null) {
            formInfo.setAdditionalInfo(formInfoDTO.getAdditionalInfo());
        }
        if (formInfoDTO.getAgeGroup() != null) {
            formInfo.setAgeGroup(formInfoDTO.getAgeGroup());
        }
        if (formInfoDTO.getFormType() != null) {
            formInfo.setFormType(formInfoDTO.getFormType());
        }
    }

    private void updateFormBookingFields(FormBooking formBooking, FormBookingDTO formBookingDTO) {
        if (formBookingDTO.getBeginTime() != null) {
            formBooking.setBeginTime(formBookingDTO.getBeginTime());
        }
        if (formBookingDTO.getEndTime() != null) {
            formBooking.setEndTime(formBookingDTO.getEndTime());
        }
        if (formBookingDTO.getParticipantsQuantity() != 0) {
            formBooking.setParticipantsQuantity(formBookingDTO.getParticipantsQuantity());
        }
        if (formBookingDTO.getGuidesQuantity() != 0) {
            formBooking.setGuidesQuantity(formBookingDTO.getGuidesQuantity());
        }
        if (formBookingDTO.getActivityType() != null) {
            Set<ActivityType> activityTypeSet = formBookingDTO.getActivityType().stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toSet());
            formBooking.setActivityType(activityTypeSet);
        }
    }

    public void deleteFormInfo(Long id) {
        formInfoRepository.deleteById(id);
    }

    public FormInfoDTO convertToDTO(FormInfo formInfo) {
        if (formInfo instanceof FormBooking) {
            FormBooking formBooking = (FormBooking) formInfo;
            FormBookingDTO formBookingDTO = new FormBookingDTO();
            formBookingDTO.setId(formBooking.getId());
            formBookingDTO.setEmail(formBooking.getEmail());
            formBookingDTO.setName(formBooking.getName());
            formBookingDTO.setSurname(formBooking.getSurname());
            formBookingDTO.setAssociation(formBooking.getAssociation());
            formBookingDTO.setPhoneNumber(formBooking.getPhoneNumber());
            formBookingDTO.setContactDate(formBooking.getContactDate());
            formBookingDTO.setAdditionalInfo(formBooking.getAdditionalInfo());
            formBookingDTO.setAgeGroup(formBooking.getAgeGroup());
            formBookingDTO.setFormType(formBooking.getFormType());
            formBookingDTO.setBeginTime(formBooking.getBeginTime());
            formBookingDTO.setEndTime(formBooking.getEndTime());
            formBookingDTO.setParticipantsQuantity(formBooking.getParticipantsQuantity());
            formBookingDTO.setGuidesQuantity(formBooking.getGuidesQuantity());
            formBookingDTO.setActivityType(formBooking.getActivityType().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toSet()));
            return formBookingDTO;
        } else {
            FormInfoDTO formInfoDTO = new FormInfoDTO();
            formInfoDTO.setId(formInfo.getId());
            formInfoDTO.setEmail(formInfo.getEmail());
            formInfoDTO.setName(formInfo.getName());
            formInfoDTO.setSurname(formInfo.getSurname());
            formInfoDTO.setAssociation(formInfo.getAssociation());
            formInfoDTO.setPhoneNumber(formInfo.getPhoneNumber());
            formInfoDTO.setContactDate(formInfo.getContactDate());
            formInfoDTO.setAdditionalInfo(formInfo.getAdditionalInfo());
            formInfoDTO.setAgeGroup(formInfo.getAgeGroup());
            formInfoDTO.setFormType(formInfo.getFormType());
            return formInfoDTO;
        }
    }

    private FormInfo convertToEntity(FormInfoDTO formInfoDTO) {
        FormInfo formInfo;
        if (formInfoDTO.getFormType() == FormType.FORM_BOOKING) {
            formInfo = new FormBooking();
        } else {
            formInfo = new FormInfo();
        }

        formInfo.setEmail(formInfoDTO.getEmail());
        formInfo.setName(formInfoDTO.getName());
        formInfo.setSurname(formInfoDTO.getSurname());
        formInfo.setAssociation(formInfoDTO.getAssociation());
        formInfo.setPhoneNumber(formInfoDTO.getPhoneNumber());
        formInfo.setContactDate(formInfoDTO.getContactDate());
        formInfo.setAdditionalInfo(formInfoDTO.getAdditionalInfo());
        formInfo.setAgeGroup(formInfoDTO.getAgeGroup());
        formInfo.setFormType(formInfoDTO.getFormType());

        if (formInfo instanceof FormBooking formBooking) {
            FormBookingDTO formBookingDTO = (FormBookingDTO) formInfoDTO;
            formBooking.setBeginTime(formBookingDTO.getBeginTime());
            formBooking.setEndTime(formBookingDTO.getEndTime());
            formBooking.setParticipantsQuantity(formBookingDTO.getParticipantsQuantity());
            formBooking.setGuidesQuantity(formBookingDTO.getGuidesQuantity());
            Set<ActivityType> activityTypeSet = formBookingDTO.getActivityType().stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ActivityType::getId))));
            formBooking.setActivityType(activityTypeSet);
        }

        return formInfo;
    }
    
    private ActivityType convertToEntity(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = new ActivityType();
        activityType.setId(activityTypeDTO.getId());
        activityType.setName(activityTypeDTO.getName());
        activityType.setDescription(activityTypeDTO.getDescription());
        return activityType;
    }
    
    private ActivityTypeDTO convertToDTO(ActivityType activityType) {
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO();
        activityTypeDTO.setId(activityType.getId());
        activityTypeDTO.setName(activityType.getName());
        activityTypeDTO.setDescription(activityType.getDescription());
        return activityTypeDTO;
    }
}