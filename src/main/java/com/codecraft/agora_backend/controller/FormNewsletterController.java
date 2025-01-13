package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.FormNewsletterDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FormNewsletterService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formnewsletter")
@CrossOrigin(origins = "http://localhost:3000")
public class FormNewsletterController {

    private final FormNewsletterService formNewsletterService;

    public FormNewsletterController(FormNewsletterService formNewsletterService) {
        this.formNewsletterService = formNewsletterService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FormNewsletterDTO> getAllFormNewsletters() {
        return formNewsletterService.getAllFormNewsletters();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormNewsletterDTO> getFormNewsletterById(@PathVariable Long id) {
        Optional<FormNewsletterDTO> formNewsletter = formNewsletterService.getFormNewsletterById(id);
        return formNewsletter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormNewsletterDTO> createFormNewsletter(@RequestBody FormNewsletterDTO formNewsletterDTO) {
        FormNewsletterDTO createdFormNewsletter = formNewsletterService.createFormNewsletter(formNewsletterDTO);
        return ResponseEntity.ok(createdFormNewsletter);
    }

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormNewsletter(@PathVariable Long id) {
        formNewsletterService.deleteFormNewsletter(id);
        return ResponseEntity.noContent().build();
    }
}