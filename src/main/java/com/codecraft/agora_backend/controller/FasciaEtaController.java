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
@RequestMapping("/api/fasciaeta")
public class FasciaEtaController {

    private final AgeGroupService ageGroupService;

    public FasciaEtaController(AgeGroupService ageGroupService) {
        this.ageGroupService = ageGroupService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<AgeGroupDTO> getAllFasciaEta() {
        return ageGroupService.getAllAgeGroup();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<AgeGroupDTO> getFasciaEtaById(@PathVariable Long id) {
        Optional<AgeGroupDTO> fasciaEta = ageGroupService.getAgeGroupById(id);
        return fasciaEta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<AgeGroupDTO> createFasciaEta(@RequestBody AgeGroupDTO ageGroupDTO) {
        AgeGroupDTO createdFasciaEta = ageGroupService.createAgeGroup(ageGroupDTO);
        return ResponseEntity.ok(createdFasciaEta);
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<AgeGroupDTO> updateFasciaEta(@PathVariable Long id, @RequestBody AgeGroupDTO ageGroupDTO) {
        AgeGroupDTO updatedFasciaEta = ageGroupService.updateAgeGroup(id, ageGroupDTO);
        if (updatedFasciaEta != null) {
            return ResponseEntity.ok(updatedFasciaEta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFasciaEta(@PathVariable Long id) {
        ageGroupService.deleteAgeGroup(id);
        return ResponseEntity.noContent().build();
    }
}