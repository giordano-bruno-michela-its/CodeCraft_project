package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.model.TipoAttivita;
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
    public List<TipoAttivita> getAllTipoAttivita() {
        return tipoAttivitaService.getAllTipoAttivita();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<TipoAttivita> getTipoAttivitaById(@PathVariable Long id) {
        Optional<TipoAttivita> tipoAttivita = tipoAttivitaService.getTipoAttivitaById(id);
        return tipoAttivita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<TipoAttivita> createTipoAttivita(@RequestBody TipoAttivita tipoAttivita) {
        TipoAttivita createdTipoAttivita = tipoAttivitaService.createTipoAttivita(tipoAttivita);
        return ResponseEntity.ok(createdTipoAttivita);
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<TipoAttivita> updateTipoAttivita(@PathVariable Long id, @RequestBody TipoAttivita tipoAttivita) {
        TipoAttivita updatedTipoAttivita = tipoAttivitaService.updateTipoAttivita(id, tipoAttivita);
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