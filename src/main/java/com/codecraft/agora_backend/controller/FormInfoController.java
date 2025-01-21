package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.CodeEmailRequestDTO;
import com.codecraft.agora_backend.dto.FormBookingDTO;
import com.codecraft.agora_backend.dto.FormInfoDTO;
import com.codecraft.agora_backend.model.FormBooking;
import com.codecraft.agora_backend.model.FormInfo;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FormInfoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formreq")
@CrossOrigin(origins = "http://localhost:3000")
public class FormInfoController {

    private final FormInfoService formInfoService;

    public FormInfoController(FormInfoService formInfoService) {
        this.formInfoService = formInfoService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FormInfoDTO> getAllFormInfo() {
        return formInfoService.getAllFormInfo().stream().map(formInfoService::convertToDTO).toList();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormInfoDTO> getFormInfoById(@PathVariable Long id) {
        Optional<FormInfo> formRichiesta = formInfoService.getFormInfoById(id);
        return formRichiesta.map(value -> ResponseEntity.ok(formInfoService.convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/code")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormInfoDTO> getFormInfoByCode(@RequestBody CodeEmailRequestDTO emailDTO) {
        String email = emailDTO.getEmail();
        String code = emailDTO.getCode();
        Optional<FormInfo> formSearch = formInfoService.getFormEmailCode(email, code);
        return formSearch.map(formInfo -> ResponseEntity.ok(formInfoService.convertToDTO(formInfo))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormInfoDTO> createFormInfo(@RequestBody FormInfoDTO formInfoDTO) {
        FormInfo formInfo = formInfoService.createFormInfo(formInfoDTO);
        return ResponseEntity.ok(formInfoService.convertToDTO(formInfo));
    }

    @PostMapping("/createbooking")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormBookingDTO> createFormBooking(@RequestBody FormBookingDTO formBookingDTO) {
        FormBooking formBooking = formInfoService.createFormBooking(formBookingDTO);
        return ResponseEntity.ok((FormBookingDTO) formInfoService.convertToDTO(formBooking));
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormInfoDTO> updateFormInfo(@PathVariable Long id, @RequestBody FormInfoDTO formInfoDTO) {
        FormInfo updatedFormInfo = formInfoService.updateFormInfo(id, formInfoDTO);
        if (updatedFormInfo != null) {
            return ResponseEntity.ok(formInfoService.convertToDTO(updatedFormInfo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateinfonomail/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormInfoDTO> updateFormInfoNoMail(@PathVariable Long id, @RequestBody FormInfoDTO formInfoDTO) {
        FormInfo updatedFormInfo = formInfoService.updateFormInfoNoMail(id, formInfoDTO);
        if (updatedFormInfo != null) {
            return ResponseEntity.ok(formInfoService.convertToDTO(updatedFormInfo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatebooking/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormBookingDTO> updateFormBooking(@PathVariable Long id, @RequestBody FormBookingDTO formBookingDTO) {
        FormBooking updatedFormBooking = formInfoService.updateFormBooking(id, formBookingDTO);
        if (updatedFormBooking != null) {
            return ResponseEntity.ok((FormBookingDTO) formInfoService.convertToDTO(updatedFormBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatebookingnomail/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormBookingDTO> updateFormBookingNoMail(@PathVariable Long id, @RequestBody FormBookingDTO formBookingDTO) {
        FormBooking updatedFormBooking = formInfoService.updateFormBookingNoMail(id, formBookingDTO);
        if (updatedFormBooking != null) {
            return ResponseEntity.ok((FormBookingDTO) formInfoService.convertToDTO(updatedFormBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormInfo(@PathVariable Long id) {
        formInfoService.deleteFormInfo(id);
        return ResponseEntity.noContent().build();
    }
}