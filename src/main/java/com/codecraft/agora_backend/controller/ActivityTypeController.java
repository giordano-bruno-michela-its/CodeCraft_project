package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.ActivityTypeDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.ActivityTypeService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing activity types.
 */
@RestController
@RequestMapping("/api/activitytype")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityTypeController {

    private final ActivityTypeService activityTypeService;

    /**
     * Constructor for ActivityTypeController.
     *
     * @param activityTypeService the activity type service
     */
    public ActivityTypeController(ActivityTypeService activityTypeService) {
        this.activityTypeService = activityTypeService;
    }

    /**
     * Get all activity types.
     *
     * @return the list of all activity types
     */
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<ActivityTypeDTO> getAllTipoAttivita() {
        return activityTypeService.getAllActivityType();
    }

    /**
     * Get an activity type by ID.
     *
     * @param id the activity type ID
     * @return the activity type with the given ID
     */
    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<ActivityTypeDTO> getTipoAttivitaById(@PathVariable Long id) {
        Optional<ActivityTypeDTO> tipoAttivita = activityTypeService.getActivityTypeById(id);
        return tipoAttivita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new activity type.
     *
     * @param activityTypeDTO the activity type to create
     * @return the created activity type
     */
    @PostMapping("/create")
    @JsonView(View.SubView.class)
    public ResponseEntity<ActivityTypeDTO> createTipoAttivita(@RequestBody ActivityTypeDTO activityTypeDTO) {
        ActivityTypeDTO createdTipoAttivita = activityTypeService.createActivityType(activityTypeDTO);
        return ResponseEntity.ok(createdTipoAttivita);
    }

    /**
     * Update an existing activity type.
     *
     * @param id the activity type ID
     * @param activityTypeDTO the activity type data to update
     * @return the updated activity type
     */
    @PutMapping("/update/{id}")
    @JsonView(View.SubView.class)
    public ResponseEntity<ActivityTypeDTO> updateTipoAttivita(@PathVariable Long id, @RequestBody ActivityTypeDTO activityTypeDTO) {
        ActivityTypeDTO updatedTipoAttivita = activityTypeService.updateActivityType(id, activityTypeDTO);
        if (updatedTipoAttivita != null) {
            return ResponseEntity.ok(updatedTipoAttivita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete an activity type.
     *
     * @param id the activity type ID
     * @return no content response
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTipoAttivita(@PathVariable Long id) {
        activityTypeService.deleteActivityType(id);
        return ResponseEntity.noContent().build();
    }
}