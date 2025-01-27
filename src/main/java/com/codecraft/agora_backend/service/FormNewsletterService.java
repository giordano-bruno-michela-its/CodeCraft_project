package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.FormNewsletterDTO;
import com.codecraft.agora_backend.model.FormNewsletter;
import com.codecraft.agora_backend.model.NewsletterCheck;
import com.codecraft.agora_backend.repository.FormNewsletterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for FormNewsletter
 */
@Service
public class FormNewsletterService {

    private final FormNewsletterRepository formNewsletterRepository;

    /**
     * Constructor for FormNewsletterService
     *
     * @param formNewsletterRepository Repository for FormNewsletter
     */
    public FormNewsletterService(FormNewsletterRepository formNewsletterRepository) {
        this.formNewsletterRepository = formNewsletterRepository;
    }

    /**
     * Get all form newsletters.
     *
     * @return the list of all form newsletters
     */
    public List<FormNewsletterDTO> getAllFormNewsletters() {
        return formNewsletterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a form newsletter by ID.
     *
     * @param id the form newsletter ID
     * @return the form newsletter with the given ID
     */
    public Optional<FormNewsletterDTO> getFormNewsletterById(Long id) {
        return formNewsletterRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Create a new form newsletter.
     *
     * @param formNewsletterDTO the form newsletter data to create
     * @return the created form newsletter
     */
    public FormNewsletterDTO createFormNewsletter(FormNewsletterDTO formNewsletterDTO) {
        FormNewsletter formNewsletter = convertToEntity(formNewsletterDTO);
        return convertToDTO(formNewsletterRepository.save(formNewsletter));
    }

    /**
     * Update an existing form newsletter.
     *
     * @param id                the form newsletter ID
     * @param formNewsletterDTO the form newsletter data to update
     * @return the updated form newsletter
     */
    public FormNewsletterDTO updateFormNewsletter(Long id, FormNewsletterDTO formNewsletterDTO) {
        Optional<FormNewsletter> optionalFormNewsletter = formNewsletterRepository.findById(id);
        if (optionalFormNewsletter.isPresent()) {
            FormNewsletter formNewsletter = optionalFormNewsletter.get();
            updateFields(formNewsletter, formNewsletterDTO);
            return convertToDTO(formNewsletterRepository.save(formNewsletter));
        }
        return null;
    }

    /**
     * Delete a form newsletter by ID.
     *
     * @param id the form newsletter ID
     */
    public void deleteFormNewsletter(Long id) {
        formNewsletterRepository.deleteById(id);
    }

    /**
     * Update the fields of a form newsletter.
     *
     * @param formNewsletter    the form newsletter entity
     * @param formNewsletterDTO the form newsletter data transfer object
     */
    private void updateFields(FormNewsletter formNewsletter, FormNewsletterDTO formNewsletterDTO) {
        if (formNewsletterDTO.getEmail() != null) {
            formNewsletter.setEmail(formNewsletterDTO.getEmail());
        }
        if (formNewsletterDTO.getName() != null) {
            formNewsletter.setName(formNewsletterDTO.getName());
        }
        if (formNewsletterDTO.getSurname() != null) {
            formNewsletter.setSurname(formNewsletterDTO.getSurname());
        }
        if (formNewsletterDTO.getContactDate() != null) {
            formNewsletter.setContactDate(formNewsletterDTO.getContactDate());
        }
        if (formNewsletterDTO.getNewsletterCheck() != null) {
            formNewsletter.setNewsletterCheck(formNewsletterDTO.getNewsletterCheck());
        }
        if (formNewsletterDTO.getFormType() != null) {
            formNewsletter.setFormType(formNewsletterDTO.getFormType());
        }
    }

    /**
     * Convert a FormNewsletter entity to a FormNewsletterDTO.
     *
     * @param formNewsletter the form newsletter entity
     * @return the form newsletter data transfer object
     */
    private FormNewsletterDTO convertToDTO(FormNewsletter formNewsletter) {
        FormNewsletterDTO formNewsletterDTO = new FormNewsletterDTO();
        formNewsletterDTO.setId(formNewsletter.getId());
        formNewsletterDTO.setEmail(formNewsletter.getEmail());
        formNewsletterDTO.setName(formNewsletter.getName());
        formNewsletterDTO.setSurname(formNewsletter.getSurname());
        formNewsletterDTO.setContactDate(formNewsletter.getContactDate());
        formNewsletterDTO.setNewsletterCheck(formNewsletter.getNewsletterCheck());
        formNewsletterDTO.setFormType(formNewsletter.getFormType());
        return formNewsletterDTO;
    }

    /**
     * Convert a FormNewsletterDTO to a FormNewsletter entity.
     *
     * @param formNewsletterDTO the form newsletter data transfer object
     * @return the form newsletter entity
     */
    private FormNewsletter convertToEntity(FormNewsletterDTO formNewsletterDTO) {
        return FormNewsletter.builder()
                .id(formNewsletterDTO.getId())
                .email(formNewsletterDTO.getEmail())
                .name(formNewsletterDTO.getName())
                .surname(formNewsletterDTO.getSurname())
                .contactDate(formNewsletterDTO.getContactDate())
                .newsletterCheck(formNewsletterDTO.getNewsletterCheck())
                .formType(formNewsletterDTO.getFormType())
                .build();
    }

    /**
     * Get all emails from all forms with newsletterCheck field set on "YES".
     *
     * @return the list of emails for newsletter subscription
     */
    public List<String> getEmailsForNewsletter() {
        return formNewsletterRepository.findAll().stream()
                .filter(formNewsletter -> formNewsletter.getNewsletterCheck() == NewsletterCheck.YES)
                .map(FormNewsletter::getEmail)
                .collect(Collectors.toList());
    }
}