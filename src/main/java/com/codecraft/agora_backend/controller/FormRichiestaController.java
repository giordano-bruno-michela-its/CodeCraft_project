package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.FormPrenotazioneDTO;
import com.codecraft.agora_backend.dto.FormRichiestaDTO;
import com.codecraft.agora_backend.model.FormPrenotazione;
import com.codecraft.agora_backend.model.FormRichiesta;
import com.codecraft.agora_backend.model.TipoRichiesta;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FormRichiestaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formreq")
public class FormRichiestaController {

    @Autowired
    private FormRichiestaService formRichiestaService;

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FormRichiesta> getAllFormRichieste() {
        return formRichiestaService.getAllFormRichieste();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormRichiesta> getFormRichiestaById(@PathVariable Long id) {
        Optional<FormRichiesta> formRichiesta = formRichiestaService.getFormRichiestaById(id);
        return formRichiesta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormRichiesta> createFormRichiesta(@RequestBody FormRichiestaDTO formRichiestaDTO) {
        FormRichiesta formRichiesta = formRichiestaService.createFormRichiesta(formRichiestaDTO);
        return ResponseEntity.ok(formRichiesta);
    }

    @JsonView(View.PostView.class)
    @PostMapping("/createprenot")
    public ResponseEntity<FormPrenotazione> createFormPrenotazione(@RequestBody FormPrenotazioneDTO formPrenotazioneDTO) {
        FormPrenotazione formPrenotazione = formRichiestaService.createFormPrenotazione(formPrenotazioneDTO);
        return ResponseEntity.ok(formPrenotazione);
    }

    @JsonView(View.PostView.class)
    @PutMapping("/update/{id}")
    public ResponseEntity<FormRichiesta> updateFormRichiesta(@PathVariable Long id, @RequestBody FormRichiestaDTO formRichiestaDTO) {
        FormRichiesta updatedFormRichiesta = formRichiestaService.updateFormRichiesta(id, formRichiestaDTO);
        if (updatedFormRichiesta != null) {
            return ResponseEntity.ok(updatedFormRichiesta);
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