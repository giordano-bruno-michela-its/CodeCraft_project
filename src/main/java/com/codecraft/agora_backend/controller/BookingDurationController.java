package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.BookingDurationDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.BookingDurationService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing booking durations.
 */
@Tag(name = "BookingDurationController", description = "Controller for managing booking durations")
@RestController
@RequestMapping("/api/bookingduration")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingDurationController {

    private final BookingDurationService bookingDurationService;

    /**
     * Constructor for BookingDurationController.
     *
     * @param bookingDurationService the booking duration service
     */
    public BookingDurationController(BookingDurationService bookingDurationService) {
        this.bookingDurationService = bookingDurationService;
    }

    /**
     * Get all booking durations.
     *
     * @return the list of all booking durations
     */
    @Operation(summary = "Get all booking durations", description = "Retrieve a list of all booking durations")
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<BookingDurationDTO> getAllBookingDuration() {
        return bookingDurationService.getAllBookingDuration();
    }

    /**
     * Get a booking duration by ID.
     *
     * @param id the booking duration ID
     * @return the booking duration with the given ID
     */
    @Operation(summary = "Get booking duration by ID", description = "Retrieve a booking duration by its ID")
    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<BookingDurationDTO> getBookingDurationById(@PathVariable Long id) {
        Optional<BookingDurationDTO> bookingDuration = bookingDurationService.getBookingDurationById(id);
        return bookingDuration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new booking duration.
     *
     * @param bookingDurationDTO the booking duration to create
     * @return the created booking duration
     */
    @Operation(summary = "Create new booking duration", description = "Create a new booking duration")
    @PostMapping("/create")
    @JsonView(View.SubView.class)
    public ResponseEntity<BookingDurationDTO> createBookingDuration(@RequestBody BookingDurationDTO bookingDurationDTO) {
        BookingDurationDTO createdBookingDuration = bookingDurationService.createBookingDuration(bookingDurationDTO);
        return ResponseEntity.ok(createdBookingDuration);
    }

    /**
     * Update an existing booking duration.
     *
     * @param id the booking duration ID
     * @param bookingDurationDTO the booking duration data to update
     * @return the updated booking duration
     */
    @Operation(summary = "Update booking duration by ID", description = "Update an existing booking duration by its ID")
    @PutMapping("/update/{id}")
    @JsonView(View.SubView.class)
    public ResponseEntity<BookingDurationDTO> updateBookingDuration(@PathVariable Long id, @RequestBody BookingDurationDTO bookingDurationDTO) {
        BookingDurationDTO updatedBookingDuration = bookingDurationService.updateBookingDuration(id, bookingDurationDTO);
        if (updatedBookingDuration != null) {
            return ResponseEntity.ok(updatedBookingDuration);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a booking duration.
     *
     * @param id the booking duration ID
     * @return no content response
     */
    @Operation(summary = "Delete booking duration by ID", description = "Delete a booking duration by its ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookingDuration(@PathVariable Long id) {
        bookingDurationService.deleteBookingDuration(id);
        return ResponseEntity.noContent().build();
    }
}