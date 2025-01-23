package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.FormBookingDTO;
import com.codecraft.agora_backend.dto.FormInfoDTO;
import com.codecraft.agora_backend.dto.ActivityTypeDTO;
import com.codecraft.agora_backend.model.*;
import com.codecraft.agora_backend.repository.FormInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing form information.
 */
@Service
public class FormInfoService {

    @Autowired
    private SendEmailService sendEmailService;

    private final FormInfoRepository formInfoRepository;

    private final Random random = new SecureRandom();

    /**
     * Constructor for FormInfoService.
     *
     * @param formInfoRepository the repository for form information
     */
    public FormInfoService(FormInfoRepository formInfoRepository) {
        this.formInfoRepository = formInfoRepository;
    }

    /**
     * Get all form information.
     *
     * @return the list of all form information
     */
    public List<FormInfo> getAllFormInfo() {
        return formInfoRepository.findAll();
    }

    /**
     * Get form information by ID.
     *
     * @param id the form information ID
     * @return the form information with the given ID
     */
    public Optional<FormInfo> getFormInfoById(Long id) {
        return formInfoRepository.findById(id);
    }

    /**
     * Checks if email and unique code are valid and returns the formInfo/formBooking to be updated if found.
     *
     * @param email the email of the form
     * @param code  the unique code of the form
     * @return the form information if the given email and unique code match
     */
    public Optional<FormInfo> getFormEmailCode(String email, String code) {
        return formInfoRepository.findByEmailAndUniqueCode(email, code);
    }

    /**
     * Create a new form information.
     *
     * @param formInfoDTO the form information data to create
     * @return the created form information
     */
    public FormInfo createFormInfo(FormInfoDTO formInfoDTO) {
        FormInfo formInfo = convertToEntity(formInfoDTO);
        sendEmailService.sendEmailInformation(formInfo);
        sendEmailService.sendInfoToAdmin(formInfo);
        return formInfoRepository.save(formInfo);
    }

    /**
     * Create a new form booking.
     *
     * @param formBookingDTO the form booking data to create
     * @return the created form booking
     */
    public FormBooking createFormBooking(FormBookingDTO formBookingDTO) {
        FormBooking formBooking = (FormBooking) convertToEntity(formBookingDTO);
        formBooking.setUniqueCode(generateUniqueCode(6)); // Set the length of the unique code
        sendEmailService.sendEmailBooking(formBooking);
        sendEmailService.sendBookingToAdmin(formBooking);
        return formInfoRepository.save(formBooking);
    }

    /**
     * Update an existing form information.
     *
     * @param id          the form information ID
     * @param formInfoDTO the form information data to update
     * @return the updated form information
     */
    public FormInfo updateFormInfo(Long id, FormInfoDTO formInfoDTO) {
        Optional<FormInfo> optionalFormInfo = formInfoRepository.findById(id);
        if (optionalFormInfo.isPresent()) {
            FormInfo formInfo = optionalFormInfo.get();
            updateCommonFields(formInfo, formInfoDTO);
            sendEmailService.sendEmailInformation(formInfo);
            sendEmailService.sendInfoToAdmin(formInfo);
            return formInfoRepository.save(formInfo);
        }
        return null;
    }

    /**
     * Update an existing form information without sending emails.
     *
     * @param id          the form information ID
     * @param formInfoDTO the form information data to update
     * @return the updated form information
     */
    public FormInfo updateFormInfoNoMail(Long id, FormInfoDTO formInfoDTO) {
        Optional<FormInfo> optionalFormInfo = formInfoRepository.findById(id);
        if (optionalFormInfo.isPresent()) {
            FormInfo formInfo = optionalFormInfo.get();
            updateCommonFields(formInfo, formInfoDTO);
            return formInfoRepository.save(formInfo);
        }
        return null;
    }

    /**
     * Update an existing form booking.
     *
     * @param id             the form booking ID
     * @param formBookingDTO the form booking data to update
     * @return the updated form booking
     */
    public FormBooking updateFormBooking(Long id, FormBookingDTO formBookingDTO) {
        Optional<FormInfo> optionalFormInfo = formInfoRepository.findById(id);
        if (optionalFormInfo.isPresent() && optionalFormInfo.get() instanceof FormBooking formBooking) {
            updateCommonFields(formBooking, formBookingDTO);
            updateFormBookingFields(formBooking, formBookingDTO);
            sendEmailService.sendEmailBooking(formBooking);
            sendEmailService.sendBookingToAdmin(formBooking);
            return formInfoRepository.save(formBooking);
        }
        return null;
    }

    /**
     * Update an existing form booking without sending emails.
     *
     * @param id             the form booking ID
     * @param formBookingDTO the form booking data to update
     * @return the updated form booking
     */
    public FormBooking updateFormBookingNoMail(Long id, FormBookingDTO formBookingDTO) {
        Optional<FormInfo> optionalFormInfo = formInfoRepository.findById(id);
        if (optionalFormInfo.isPresent() && optionalFormInfo.get() instanceof FormBooking formBooking) {
            updateCommonFields(formBooking, formBookingDTO);
            updateFormBookingFields(formBooking, formBookingDTO);
            return formInfoRepository.save(formBooking);
        }
        return null;
    }

    /**
     * Update common fields of form information and form booking.
     *
     * @param formInfo    the form entity
     * @param formInfoDTO the form data transfer object
     */
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
        if (formInfoDTO.getNewsletterCheck() != null) {
            formInfo.setNewsletterCheck(formInfoDTO.getNewsletterCheck());
        }
        if (formInfoDTO.getAgeGroup() != null) {
            formInfo.setAgeGroup(formInfoDTO.getAgeGroup());
        }
        if (formInfoDTO.getActivityType() != null) {
            Set<ActivityType> activityTypeSet = formInfoDTO.getActivityType().stream()
                    .map(activityTypeDTO -> {
                        ActivityType activityType = new ActivityType();
                        activityType.setId(activityTypeDTO.getId());
                        return activityType;
                    })
                    .collect(Collectors.toSet());
            formInfo.setActivityType(activityTypeSet);
        }
        if (formInfoDTO.getFormType() != null) {
            formInfo.setFormType(formInfoDTO.getFormType());
        }
    }

    /**
     * Update specific fields of form booking.
     *
     * @param formBooking    the form booking entity
     * @param formBookingDTO the form booking data transfer object
     */
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
        if (formBookingDTO.getBookingDuration() != null) {
            formBooking.setBookingDuration(formBookingDTO.getBookingDuration());
        }
        if (formBookingDTO.getBookingStatus() != null) {
            formBooking.setBookingStatus(formBookingDTO.getBookingStatus());
        }
        if (formBookingDTO.getUniqueCode() != null) {
            formBooking.setUniqueCode(formBookingDTO.getUniqueCode());
        }
    }

    /**
     * Delete form by ID.
     *
     * @param id the form ID
     */
    public void deleteFormInfo(Long id) {
        formInfoRepository.deleteById(id);
    }

    /**
     * Convert a FormInfo/FormBooking entity to a FormInfoDTO.
     *
     * @param formInfo the FormInfo/FormBooking entity
     * @return the FormInfo/FormBooking data transfer object
     */
    public FormInfoDTO convertToDTO(FormInfo formInfo) {
        if (formInfo instanceof FormBooking formBooking) {
            FormBookingDTO formBookingDTO = new FormBookingDTO();
            formBookingDTO.setId(formBooking.getId());
            formBookingDTO.setEmail(formBooking.getEmail());
            formBookingDTO.setName(formBooking.getName());
            formBookingDTO.setSurname(formBooking.getSurname());
            formBookingDTO.setAssociation(formBooking.getAssociation());
            formBookingDTO.setPhoneNumber(formBooking.getPhoneNumber());
            formBookingDTO.setContactDate(formBooking.getContactDate());
            formBookingDTO.setAdditionalInfo(formBooking.getAdditionalInfo());
            formBookingDTO.setNewsletterCheck(formInfo.getNewsletterCheck());
            formBookingDTO.setAgeGroup(formBooking.getAgeGroup());
            formBookingDTO.setFormType(formBooking.getFormType());
            formBookingDTO.setBeginTime(formBooking.getBeginTime());
            formBookingDTO.setEndTime(formBooking.getEndTime());
            formBookingDTO.setParticipantsQuantity(formBooking.getParticipantsQuantity());
            formBookingDTO.setGuidesQuantity(formBooking.getGuidesQuantity());
            formBookingDTO.setActivityType(formBooking.getActivityType().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toSet()));
            formBookingDTO.setBookingDuration(formBooking.getBookingDuration());
            formBookingDTO.setBookingStatus(formBooking.getBookingStatus());
            formBookingDTO.setUniqueCode(formBooking.getUniqueCode());
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
            formInfoDTO.setNewsletterCheck(formInfo.getNewsletterCheck());
            formInfoDTO.setAgeGroup(formInfo.getAgeGroup());
            formInfoDTO.setFormType(formInfo.getFormType());
            formInfoDTO.setActivityType(formInfo.getActivityType().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toSet()));
            return formInfoDTO;
        }
    }

    /**
     * Convert a FormInfoDTO to a FormInfo/FormBooking entity.
     *
     * @param formInfoDTO the FormInfoDTO data transfer object
     * @return the FormInfo/FormBooking entity
     */
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
        formInfo.setNewsletterCheck(formInfoDTO.getNewsletterCheck());
        formInfo.setAgeGroup(formInfoDTO.getAgeGroup());
        formInfo.setFormType(formInfoDTO.getFormType());
        Set<ActivityType> activityTypeSet = formInfoDTO.getActivityType().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ActivityType::getId))));
        formInfo.setActivityType(activityTypeSet);

        if (formInfo instanceof FormBooking formBooking) {
            FormBookingDTO formBookingDTO = (FormBookingDTO) formInfoDTO;
            formBooking.setBeginTime(formBookingDTO.getBeginTime());
            formBooking.setEndTime(formBookingDTO.getEndTime());
            formBooking.setParticipantsQuantity(formBookingDTO.getParticipantsQuantity());
            formBooking.setGuidesQuantity(formBookingDTO.getGuidesQuantity());
            formBooking.setBookingDuration(formBookingDTO.getBookingDuration());
            formBooking.setBookingStatus(formBookingDTO.getBookingStatus());
            formBooking.setUniqueCode(formBookingDTO.getUniqueCode());
        }

        return formInfo;
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
     * Get all emails from all forms with newsletterCheck field set to "YES".
     *
     * @return the list of emails for newsletter subscription
     */
    public List<String> getEmailsForNewsletter() {
        return formInfoRepository.findAll().stream()
                .filter(formInfo -> formInfo.getNewsletterCheck() == NewsletterCheck.YES)
                .map(FormInfo::getEmail)
                .collect(Collectors.toList());
    }

    /**
     * Generate a unique code of the specified length.
     *
     * @param length the length of the unique code
     * @return the generated unique code
     */
    private String generateUniqueCode(int length) {
        String characters = "0123456789";
        String code;
        do {
            StringBuilder codeBuilder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                codeBuilder.append(characters.charAt(random.nextInt(characters.length())));
            }
            code = codeBuilder.toString();
        } while (formInfoRepository.existsByUniqueCode(code));
        return code;
    }
}