package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.BookingDurationDTO;
import com.codecraft.agora_backend.model.BookingDuration;
import com.codecraft.agora_backend.repository.BookingDurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing booking durations.
 */
@Service
public class BookingDurationService {

    private final BookingDurationRepository bookingDurationRepository;

    /**
     * Constructor for BookingDurationService.
     *
     * @param bookingDurationRepository the repository for booking durations
     */
    public BookingDurationService(BookingDurationRepository bookingDurationRepository) {
        this.bookingDurationRepository = bookingDurationRepository;
    }

    /**
     * Get all booking durations.
     *
     * @return the list of all booking durations
     */
    public List<BookingDurationDTO> getAllBookingDuration() {
        return bookingDurationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Get booking duration by ID.
     *
     * @param id the booking duration ID
     * @return the booking duration with the given ID
     */
    public Optional<BookingDurationDTO> getBookingDurationById(Long id) {
        return bookingDurationRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Create a new booking duration.
     *
     * @param bookingDurationDTO the booking duration data to create
     * @return the created booking duration
     */
    public BookingDurationDTO createBookingDuration(BookingDurationDTO bookingDurationDTO) {
        BookingDuration bookingDuration = convertToEntity(bookingDurationDTO);
        return convertToDTO(bookingDurationRepository.save(bookingDuration));
    }

    /**
     * Update an existing booking duration.
     *
     * @param id                 the booking duration ID
     * @param bookingDurationDTO the booking duration data to update
     * @return the updated booking duration
     */
    public BookingDurationDTO updateBookingDuration(Long id, BookingDurationDTO bookingDurationDTO) {
        Optional<BookingDuration> optionalBookingDuration = bookingDurationRepository.findById(id);
        if (optionalBookingDuration.isPresent()) {
            BookingDuration existingBookingDuration = optionalBookingDuration.get();

            if (bookingDurationDTO.getName() != null) {
                existingBookingDuration.setName(bookingDurationDTO.getName());
            }
            if (bookingDurationDTO.getDescription() != null) {
                existingBookingDuration.setDescription(bookingDurationDTO.getDescription());
            }

            return convertToDTO(bookingDurationRepository.save(existingBookingDuration));
        }
        return null;
    }

    /**
     * Delete booking duration by ID.
     *
     * @param id the booking duration ID
     */
    public void deleteBookingDuration(Long id) {
        bookingDurationRepository.deleteById(id);
    }

    /**
     * Convert a BookingDuration entity to a BookingDurationDTO.
     *
     * @param bookingDuration the booking duration entity
     * @return the booking duration data transfer object
     */
    private BookingDurationDTO convertToDTO(BookingDuration bookingDuration) {
        BookingDurationDTO bookingDurationDTO = new BookingDurationDTO();
        bookingDurationDTO.setId(bookingDuration.getId());
        bookingDurationDTO.setName(bookingDuration.getName());
        bookingDurationDTO.setDescription(bookingDuration.getDescription());
        return bookingDurationDTO;
    }

    /**
     * Convert a BookingDurationDTO to a BookingDuration entity.
     *
     * @param bookingDurationDTO the booking duration data transfer object
     * @return the booking duration entity
     */
    private BookingDuration convertToEntity(BookingDurationDTO bookingDurationDTO) {
        BookingDuration bookingDuration = new BookingDuration();
        bookingDuration.setId(bookingDurationDTO.getId());
        bookingDuration.setName(bookingDurationDTO.getName());
        bookingDuration.setDescription(bookingDurationDTO.getDescription());
        return bookingDuration;
    }
}