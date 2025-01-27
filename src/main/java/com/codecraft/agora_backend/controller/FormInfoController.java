package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.CodeEmailRequestDTO;
import com.codecraft.agora_backend.dto.FormBookingDTO;
import com.codecraft.agora_backend.dto.FormInfoDTO;
import com.codecraft.agora_backend.dto.UpdateFormBookingRequestDTO;
import com.codecraft.agora_backend.model.FormBooking;
import com.codecraft.agora_backend.model.FormInfo;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.FormInfoService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for handling formInfo/formBooking requests.
 */
@RestController
@RequestMapping("/api/formreq")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "FormInfoController", description = "Controller for managing form information requests")
public class FormInfoController {

    private final FormInfoService formInfoService;

    /**
     * Constructor for FormInfoController.
     *
     * @param formInfoService service for handling form information requests
     */
    public FormInfoController(FormInfoService formInfoService) {
        this.formInfoService = formInfoService;
    }

    /**
     * Get all formInfo/formBooking records.
     *
     * @return a list of formInfo/formBooking records.
     */
    @Operation(summary = "Get all formInfo/formBooking records", description = "Retrieve a list of all formInfo/formBooking records")
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<FormInfoDTO> getAllFormInfo() {
        return formInfoService.getAllFormInfo().stream().map(formInfoService::convertToDTO).toList();
    }

    /**
     * Get a formInfo/formBooking by ID.
     *
     * @param id the ID of the formInfo/formBooking to retrieve
     * @return a ResponseEntity containing the FormInfoDTO if found, otherwise a 404 response
     */
    @Operation(summary = "Get formInfo/formBooking by ID", description = "Retrieve a formInfo/formBooking by its ID")
    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormInfoDTO> getFormInfoById(@PathVariable Long id) {
        Optional<FormInfo> formRichiesta = formInfoService.getFormInfoById(id);
        return formRichiesta.map(value -> ResponseEntity.ok(formInfoService.convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Checks if email and unique code are valid and returns the formInfo/formBooking to be updated if found.
     *
     * @param emailDTO the email and code to check
     * @return a ResponseEntity containing the FormInfoDTO if found, otherwise a 404 response
     */
    @Operation(summary = "Check email and code", description = "Check if email and unique code are valid and return the formInfo/formBooking to be updated if found")
    @PutMapping("/code")
    @JsonView(View.GetView.class)
    public ResponseEntity<FormInfoDTO> getFormInfoByCode(@RequestBody CodeEmailRequestDTO emailDTO) {
        String email = emailDTO.getEmail();
        String code = emailDTO.getCode();
        Optional<FormInfo> formSearch = formInfoService.getFormEmailCode(email, code);
        return formSearch.map(formInfo -> ResponseEntity.ok(formInfoService.convertToDTO(formInfo))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new formInfo.
     *
     * @param formInfoDTO the form information
     * @return a ResponseEntity containing the created FormInfo
     */
    @Operation(summary = "Create new formInfo", description = "Create a new formInfo")
    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormInfoDTO> createFormInfo(@RequestBody FormInfoDTO formInfoDTO) {
        FormInfo formInfo = formInfoService.createFormInfo(formInfoDTO);
        return ResponseEntity.ok(formInfoService.convertToDTO(formInfo));
    }

    /**
     * Create a new formBooking.
     *
     * @param formBookingDTO the formBooking
     * @return a ResponseEntity containing the created FormBooking
     */
    @Operation(summary = "Create new formBooking", description = "Create a new formBooking")
    @PostMapping("/createbooking")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormBookingDTO> createFormBooking(@RequestBody FormBookingDTO formBookingDTO) {
        FormBooking formBooking = formInfoService.createFormBooking(formBookingDTO);
        return ResponseEntity.ok((FormBookingDTO) formInfoService.convertToDTO(formBooking));
    }

    /**
     * Modify formInfo by ID.
     *
     * @param id          the ID of the formInfo to update
     * @param formInfoDTO the form information to update
     * @return a ResponseEntity containing the updated FormInfo if found, otherwise a 404 response
     */
    @Operation(summary = "Update formInfo by ID", description = "Modify formInfo by ID")
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

    /**
     * Modify formInfo by ID, without sending e-mails.
     * To be used when the form must be updated without sending e-mails like when it's created.
     *
     * @param id          the ID of the formInfo to update
     * @param formInfoDTO the form information to update
     * @return a ResponseEntity containing the updated FormInfo if found, otherwise a 404 response
     */
    @Operation(summary = "Update formInfo by ID without sending emails", description = "Modify formInfo by ID without sending emails")
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

    /**
     * Modify formBooking by ID.
     *
     * @param id             the ID of the formBooking to update
     * @param formBookingDTO formBooking to update
     * @return a ResponseEntity containing the updated formBooking if found, otherwise a 404 response
     */
    @Operation(summary = "Update formBooking by ID", description = "Modify formBooking by ID")
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

    /**
     * Modify formBooking by ID, without sending e-mails.
     * To be used when the form must be updated without sending e-mails like when it's created.
     *
     * @param id             the ID of the formBooking to update
     * @param formBookingDTO formBooking to update
     * @return a ResponseEntity containing the updated formBooking if found, otherwise a 404 response
     */
    @Operation(summary = "Update formBooking by ID without sending emails", description = "Modify formBooking by ID without sending emails")
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

    /**
     * Delete form by ID.
     *
     * @param id the ID of the form to delete
     * @return a ResponseEntity containing a 204 response
     */
    @Operation(summary = "Delete form by ID", description = "Delete form by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormInfo(@PathVariable Long id) {
        formInfoService.deleteFormInfo(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update formBooking by code", description = "Update formBooking by code and email")
    @PutMapping("/updatefromcode")
    @JsonView(View.PostView.class)
    public ResponseEntity<FormBookingDTO> updateFormBookingByCode(@RequestBody UpdateFormBookingRequestDTO requestDTO) {
        String email = requestDTO.getCodeEmailRequest().getEmail();
        String code = requestDTO.getCodeEmailRequest().getCode();
        FormBookingDTO formBookingDTO = requestDTO.getFormBooking();

        Optional<FormInfo> formSearch = formInfoService.getFormEmailCode(email, code);
        if (formSearch.isPresent() && formSearch.get() instanceof FormBooking formBooking) {
            formInfoService.updateCommonFields(formBooking, formBookingDTO);
            formInfoService.updateFormBookingFields(formBooking, formBookingDTO);
            FormBooking updatedFormBooking = formInfoService.saveFormBooking(formBooking);
            return ResponseEntity.ok((FormBookingDTO) formInfoService.convertToDTO(updatedFormBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}