package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.AgeGroupDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.AgeGroupService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agegroup")
@CrossOrigin(origins = "http://localhost:3000")
public class AgeGroupController {

    private final AgeGroupService ageGroupService;

    public AgeGroupController(AgeGroupService ageGroupService) {
        this.ageGroupService = ageGroupService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<AgeGroupDTO> getAllAgeGroup() {
        return ageGroupService.getAllAgeGroup();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<AgeGroupDTO> getAgeGroupById(@PathVariable Long id) {
        Optional<AgeGroupDTO> ageGroup = ageGroupService.getAgeGroupById(id);
        return ageGroup.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<AgeGroupDTO> createAgeGroup(@RequestBody AgeGroupDTO ageGroupDTO) {
        AgeGroupDTO createdAgeGroup = ageGroupService.createAgeGroup(ageGroupDTO);
        return ResponseEntity.ok(createdAgeGroup);
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<AgeGroupDTO> updateAgeGroup(@PathVariable Long id, @RequestBody AgeGroupDTO ageGroupDTO) {
        AgeGroupDTO updatedAgeGroup = ageGroupService.updateAgeGroup(id, ageGroupDTO);
        if (updatedAgeGroup != null) {
            return ResponseEntity.ok(updatedAgeGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgeGroup(@PathVariable Long id) {
        ageGroupService.deleteAgeGroup(id);
        return ResponseEntity.noContent().build();
    }
}