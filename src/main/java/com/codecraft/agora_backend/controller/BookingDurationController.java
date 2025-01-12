package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.BookingDurationDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.BookingDurationService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookingduration")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingDurationController {

    private final BookingDurationService bookingDurationService;

    public BookingDurationController(BookingDurationService bookingDurationService) {
        this.bookingDurationService = bookingDurationService;
    }

    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<BookingDurationDTO> getAllBookingDuration() {
        return bookingDurationService.getAllBookingDuration();
    }

    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<BookingDurationDTO> getBookingDurationById(@PathVariable Long id) {
        Optional<BookingDurationDTO> bookingDuration = bookingDurationService.getBookingDurationById(id);
        return bookingDuration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @JsonView(View.PostView.class)
    public ResponseEntity<BookingDurationDTO> createBookingDuration(@RequestBody BookingDurationDTO bookingDurationDTO) {
        BookingDurationDTO createdBookingDuration = bookingDurationService.createBookingDuration(bookingDurationDTO);
        return ResponseEntity.ok(createdBookingDuration);
    }

    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
    public ResponseEntity<BookingDurationDTO> updateBookingDuration(@PathVariable Long id, @RequestBody BookingDurationDTO bookingDurationDTO) {
        BookingDurationDTO updatedBookingDuration = bookingDurationService.updateBookingDuration(id, bookingDurationDTO);
        if (updatedBookingDuration != null) {
            return ResponseEntity.ok(updatedBookingDuration);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookingDuration(@PathVariable Long id) {
        bookingDurationService.deleteBookingDuration(id);
        return ResponseEntity.noContent().build();
    }
}