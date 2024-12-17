package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.model.FasciaEta;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FasciaEtaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fasciaeta")
public class FasciaEtaController {

    private final FasciaEtaService fasciaEtaService;

    public FasciaEtaController(FasciaEtaService fasciaEtaService) {
        this.fasciaEtaService = fasciaEtaService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FasciaEta> getAllFasciaEta() {
        return fasciaEtaService.getAllFasciaEta();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FasciaEta> getFasciaEtaById(@PathVariable Long id) {
        Optional<FasciaEta> fasciaEta = fasciaEtaService.getFasciaEtaById(id);
        return fasciaEta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FasciaEta> createFasciaEta(@RequestBody FasciaEta fasciaEta) {
        FasciaEta createdFasciaEta = fasciaEtaService.createFasciaEta(fasciaEta);
        return ResponseEntity.ok(createdFasciaEta);
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FasciaEta> updateFasciaEta(@PathVariable Long id, @RequestBody FasciaEta fasciaEta) {
        FasciaEta updatedFasciaEta = fasciaEtaService.updateFasciaEta(id, fasciaEta);
        if (updatedFasciaEta != null) {
            return ResponseEntity.ok(updatedFasciaEta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFasciaEta(@PathVariable Long id) {
        fasciaEtaService.deleteFasciaEta(id);
        return ResponseEntity.noContent().build();
    }
}