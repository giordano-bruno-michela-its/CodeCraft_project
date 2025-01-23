package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.AgeGroupDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.AgeGroupService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing age groups.
 */
@Tag(name = "AgeGroupController", description = "Controller for managing age groups")
@RestController
@RequestMapping("/api/agegroup")
@CrossOrigin(origins = "http://localhost:3000")
public class AgeGroupController {

    private final AgeGroupService ageGroupService;

    /**
     * Constructor for AgeGroupController.
     *
     * @param ageGroupService the age group service
     */
    public AgeGroupController(AgeGroupService ageGroupService) {
        this.ageGroupService = ageGroupService;
    }

    /**
     * Get all age groups.
     *
     * @return the list of all age groups
     */
    @Operation(summary = "Get all age groups", description = "Retrieve a list of all age groups")
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<AgeGroupDTO> getAllAgeGroup() {
        return ageGroupService.getAllAgeGroup();
    }

    /**
     * Get an age group by ID.
     *
     * @param id the age group ID
     * @return the age group with the given ID
     */
    @Operation(summary = "Get age group by ID", description = "Retrieve an age group by its ID")
    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<AgeGroupDTO> getAgeGroupById(@PathVariable Long id) {
        Optional<AgeGroupDTO> ageGroup = ageGroupService.getAgeGroupById(id);
        return ageGroup.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new age group.
     *
     * @param ageGroupDTO the age group to create
     * @return the created age group
     */
    @Operation(summary = "Create new age group", description = "Create a new age group")
    @PostMapping("/create")
    @JsonView(View.SubView.class)
    public ResponseEntity<AgeGroupDTO> createAgeGroup(@RequestBody AgeGroupDTO ageGroupDTO) {
        AgeGroupDTO createdAgeGroup = ageGroupService.createAgeGroup(ageGroupDTO);
        return ResponseEntity.ok(createdAgeGroup);
    }

    /**
     * Update an existing age group.
     *
     * @param id          the age group ID
     * @param ageGroupDTO the age group data to update
     * @return the updated age group
     */
    @Operation(summary = "Update age group by ID", description = "Update an existing age group by its ID")
    @PutMapping("/update/{id}")
    @JsonView(View.SubView.class)
    public ResponseEntity<AgeGroupDTO> updateAgeGroup(@PathVariable Long id, @RequestBody AgeGroupDTO ageGroupDTO) {
        AgeGroupDTO updatedAgeGroup = ageGroupService.updateAgeGroup(id, ageGroupDTO);
        if (updatedAgeGroup != null) {
            return ResponseEntity.ok(updatedAgeGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete an age group.
     *
     * @param id the age group ID
     * @return no content response
     */
    @Operation(summary = "Delete age group by ID", description = "Delete an age group by its ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgeGroup(@PathVariable Long id) {
        ageGroupService.deleteAgeGroup(id);
        return ResponseEntity.noContent().build();
    }
}