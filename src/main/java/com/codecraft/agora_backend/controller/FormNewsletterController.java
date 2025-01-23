package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.FormNewsletterDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FormNewsletterService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for handling FormNewsletter entities.
 */
@RestController
@RequestMapping("/api/formnewsletter")
@CrossOrigin(origins = "http://localhost:3000")
public class FormNewsletterController {

    private final FormNewsletterService formNewsletterService;

    /**
     * Constructor for FormNewsletterController.
     *
     * @param formNewsletterService Service for handling FormNewsletter entities
     */
    public FormNewsletterController(FormNewsletterService formNewsletterService) {
        this.formNewsletterService = formNewsletterService;
    }

    /**
     * GET /all : Get all FormNewsletter entities.
     *
     * @return a list of FormNewsletter
     */
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FormNewsletterDTO> getAllFormNewsletters() {
        return formNewsletterService.getAllFormNewsletters();
    }

    /**
     * Get a FormNewsletter entity by ID.
     *
     * @param id the ID of the FormNewsletter entity
     * @return the ResponseEntity with status 200 (OK) and the FormNewsletter, or status 404 (Not Found)
     */
    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormNewsletterDTO> getFormNewsletterById(@PathVariable Long id) {
        Optional<FormNewsletterDTO> formNewsletter = formNewsletterService.getFormNewsletterById(id);
        return formNewsletter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new FormNewsletter entity.
     *
     * @param formNewsletterDTO the FormNewsletter to create
     * @return the ResponseEntity with status 200 (OK) and the created FormNewsletter
     */
    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormNewsletterDTO> createFormNewsletter(@RequestBody FormNewsletterDTO formNewsletterDTO) {
        FormNewsletterDTO createdFormNewsletter = formNewsletterService.createFormNewsletter(formNewsletterDTO);
        return ResponseEntity.ok(createdFormNewsletter);
    }

    /**
     * Update an existing FormNewsletter entity.
     *
     * @param id the ID of the FormNewsletter entity to update
     * @param formNewsletterDTO the FormNewsletter to update
     * @return the ResponseEntity with status 200 (OK) and the updated FormNewsletter
     */
    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormNewsletterDTO> updateFormNewsletter(@PathVariable Long id, @RequestBody FormNewsletterDTO formNewsletterDTO) {
        FormNewsletterDTO updatedFormNewsletter = formNewsletterService.updateFormNewsletter(id, formNewsletterDTO);
        if (updatedFormNewsletter != null) {
            return ResponseEntity.ok(updatedFormNewsletter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a FormNewsletter entity.
     *
     * @param id the ID of the FormNewsletter entity to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormNewsletter(@PathVariable Long id) {
        formNewsletterService.deleteFormNewsletter(id);
        return ResponseEntity.noContent().build();
    }
}