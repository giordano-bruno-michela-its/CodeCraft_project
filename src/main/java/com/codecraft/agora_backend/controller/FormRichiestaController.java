package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.FormPrenotazioneDTO;
import com.codecraft.agora_backend.dto.FormRichiestaDTO;
import com.codecraft.agora_backend.model.FormPrenotazione;
import com.codecraft.agora_backend.model.FormRichiesta;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FormRichiestaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formreq")
public class FormRichiestaController {

    private final FormRichiestaService formRichiestaService;

    public FormRichiestaController(FormRichiestaService formRichiestaService) {
        this.formRichiestaService = formRichiestaService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FormRichiestaDTO> getAllFormRichieste() {
        return formRichiestaService.getAllFormRichieste().stream().map(formRichiestaService::convertToDTO).toList();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormRichiestaDTO> getFormRichiestaById(@PathVariable Long id) {
        Optional<FormRichiesta> formRichiesta = formRichiestaService.getFormRichiestaById(id);
        return formRichiesta.map(value -> ResponseEntity.ok(formRichiestaService.convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormRichiestaDTO> createFormRichiesta(@RequestBody FormRichiestaDTO formRichiestaDTO) {
        FormRichiesta formRichiesta = formRichiestaService.createFormRichiesta(formRichiestaDTO);
        return ResponseEntity.ok(formRichiestaService.convertToDTO(formRichiesta));
    }

    @PostMapping("/createprenot")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormPrenotazioneDTO> createFormPrenotazione(@RequestBody FormPrenotazioneDTO formPrenotazioneDTO) {
        FormPrenotazione formPrenotazione = formRichiestaService.createFormPrenotazione(formPrenotazioneDTO);
        return ResponseEntity.ok((FormPrenotazioneDTO) formRichiestaService.convertToDTO(formPrenotazione));
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormRichiestaDTO> updateFormRichiesta(@PathVariable Long id, @RequestBody FormRichiestaDTO formRichiestaDTO) {
        FormRichiesta updatedFormRichiesta = formRichiestaService.updateFormRichiesta(id, formRichiestaDTO);
        if (updatedFormRichiesta != null) {
            return ResponseEntity.ok(formRichiestaService.convertToDTO(updatedFormRichiesta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormRichiesta(@PathVariable Long id) {
        formRichiestaService.deleteFormRichiesta(id);
        return ResponseEntity.noContent().build();
    }
}