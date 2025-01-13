package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.FormNewsletterDTO;
import com.codecraft.agora_backend.model.FormNewsletter;
import com.codecraft.agora_backend.repository.FormNewsletterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormNewsletterService {

    private final FormNewsletterRepository formNewsletterRepository;

    public FormNewsletterService(FormNewsletterRepository formNewsletterRepository) {
        this.formNewsletterRepository = formNewsletterRepository;
    }

    public List<FormNewsletterDTO> getAllFormNewsletters() {
        return formNewsletterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FormNewsletterDTO> getFormNewsletterById(Long id) {
        return formNewsletterRepository.findById(id)
                .map(this::convertToDTO);
    }

    public FormNewsletterDTO createFormNewsletter(FormNewsletterDTO formNewsletterDTO) {
        FormNewsletter formNewsletter = convertToEntity(formNewsletterDTO);
        return convertToDTO(formNewsletterRepository.save(formNewsletter));
    }

    public FormNewsletterDTO updateFormNewsletter(Long id, FormNewsletterDTO formNewsletterDTO) {
        Optional<FormNewsletter> optionalFormNewsletter = formNewsletterRepository.findById(id);
        if (optionalFormNewsletter.isPresent()) {
            FormNewsletter formNewsletter = optionalFormNewsletter.get();
            updateFields(formNewsletter, formNewsletterDTO);
            return convertToDTO(formNewsletterRepository.save(formNewsletter));
        }
        return null;
    }

    public void deleteFormNewsletter(Long id) {
        formNewsletterRepository.deleteById(id);
    }

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
}