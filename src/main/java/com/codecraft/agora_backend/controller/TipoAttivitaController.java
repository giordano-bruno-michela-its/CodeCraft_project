package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.TipoAttivitaDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.TipoAttivitaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoattivita")
public class TipoAttivitaController {

    private final TipoAttivitaService tipoAttivitaService;

    public TipoAttivitaController(TipoAttivitaService tipoAttivitaService) {
        this.tipoAttivitaService = tipoAttivitaService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<TipoAttivitaDTO> getAllTipoAttivita() {
        return tipoAttivitaService.getAllTipoAttivita();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<TipoAttivitaDTO> getTipoAttivitaById(@PathVariable Long id) {
        Optional<TipoAttivitaDTO> tipoAttivita = tipoAttivitaService.getTipoAttivitaById(id);
        return tipoAttivita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<TipoAttivitaDTO> createTipoAttivita(@RequestBody TipoAttivitaDTO tipoAttivitaDTO) {
        TipoAttivitaDTO createdTipoAttivita = tipoAttivitaService.createTipoAttivita(tipoAttivitaDTO);
        return ResponseEntity.ok(createdTipoAttivita);
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<TipoAttivitaDTO> updateTipoAttivita(@PathVariable Long id, @RequestBody TipoAttivitaDTO tipoAttivitaDTO) {
        TipoAttivitaDTO updatedTipoAttivita = tipoAttivitaService.updateTipoAttivita(id, tipoAttivitaDTO);
        if (updatedTipoAttivita != null) {
            return ResponseEntity.ok(updatedTipoAttivita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTipoAttivita(@PathVariable Long id) {
        tipoAttivitaService.deleteTipoAttivita(id);
        return ResponseEntity.noContent().build();
    }
}