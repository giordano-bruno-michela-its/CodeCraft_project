package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.FormBookingDTO;
import com.codecraft.agora_backend.dto.FormInfoDTO;
import com.codecraft.agora_backend.model.FormBooking;
import com.codecraft.agora_backend.model.FormInfo;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FormInfoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formreq")
@CrossOrigin(origins = "http://localhost:3000")
public class FormRichiestaController {

    private final FormInfoService formInfoService;

    public FormRichiestaController(FormInfoService formInfoService) {
        this.formInfoService = formInfoService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FormInfoDTO> getAllFormRichieste() {
        return formInfoService.getAllFormInfo().stream().map(formInfoService::convertToDTO).toList();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormInfoDTO> getFormRichiestaById(@PathVariable Long id) {
        Optional<FormInfo> formRichiesta = formInfoService.getFormInfoById(id);
        return formRichiesta.map(value -> ResponseEntity.ok(formInfoService.convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormInfoDTO> createFormRichiesta(@RequestBody FormInfoDTO formInfoDTO) {
        FormInfo formInfo = formInfoService.createFormInfo(formInfoDTO);
        return ResponseEntity.ok(formInfoService.convertToDTO(formInfo));
    }

    @PostMapping("/createprenot")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormBookingDTO> createFormPrenotazione(@RequestBody FormBookingDTO formBookingDTO) {
        FormBooking formBooking = formInfoService.createFormBooking(formBookingDTO);
        return ResponseEntity.ok((FormBookingDTO) formInfoService.convertToDTO(formBooking));
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormInfoDTO> updateFormRichiesta(@PathVariable Long id, @RequestBody FormInfoDTO formInfoDTO) {
        FormInfo updatedFormInfo = formInfoService.updateFormInfo(id, formInfoDTO);
        if (updatedFormInfo != null) {
            return ResponseEntity.ok(formInfoService.convertToDTO(updatedFormInfo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateprenot/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormBookingDTO> updateFormPrenotazione(@PathVariable Long id, @RequestBody FormBookingDTO formBookingDTO) {
        FormBooking updatedFormBooking = formInfoService.updateFormBooking(id, formBookingDTO);
        if (updatedFormBooking != null) {
            return ResponseEntity.ok((FormBookingDTO) formInfoService.convertToDTO(updatedFormBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormRichiesta(@PathVariable Long id) {
        formInfoService.deleteFormInfo(id);
        return ResponseEntity.noContent().build();
    }
}