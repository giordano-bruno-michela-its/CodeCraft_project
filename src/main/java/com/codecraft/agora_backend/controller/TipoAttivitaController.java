package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.ActivityTypeDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.ActivityTypeService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoattivita")
public class TipoAttivitaController {

    private final ActivityTypeService activityTypeService;

    public TipoAttivitaController(ActivityTypeService activityTypeService) {
        this.activityTypeService = activityTypeService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<ActivityTypeDTO> getAllTipoAttivita() {
        return activityTypeService.getAllActivityType();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<ActivityTypeDTO> getTipoAttivitaById(@PathVariable Long id) {
        Optional<ActivityTypeDTO> tipoAttivita = activityTypeService.getActivityTypeById(id);
        return tipoAttivita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<ActivityTypeDTO> createTipoAttivita(@RequestBody ActivityTypeDTO activityTypeDTO) {
        ActivityTypeDTO createdTipoAttivita = activityTypeService.createActivityType(activityTypeDTO);
        return ResponseEntity.ok(createdTipoAttivita);
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<ActivityTypeDTO> updateTipoAttivita(@PathVariable Long id, @RequestBody ActivityTypeDTO activityTypeDTO) {
        ActivityTypeDTO updatedTipoAttivita = activityTypeService.updateActivityType(id, activityTypeDTO);
        if (updatedTipoAttivita != null) {
            return ResponseEntity.ok(updatedTipoAttivita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTipoAttivita(@PathVariable Long id) {
        activityTypeService.deleteActivityType(id);
        return ResponseEntity.noContent().build();
    }
}