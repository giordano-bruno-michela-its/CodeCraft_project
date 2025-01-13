package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.BookingDurationDTO;
import com.codecraft.agora_backend.model.BookingDuration;
import com.codecraft.agora_backend.repository.BookingDurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingDurationService {

    private final BookingDurationRepository bookingDurationRepository;

    public BookingDurationService(BookingDurationRepository bookingDurationRepository) {
        this.bookingDurationRepository = bookingDurationRepository;
    }

    public List<BookingDurationDTO> getAllBookingDuration() {
        return bookingDurationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<BookingDurationDTO> getBookingDurationById(Long id) {
        return bookingDurationRepository.findById(id).map(this::convertToDTO);
    }

    public BookingDurationDTO createBookingDuration(BookingDurationDTO bookingDurationDTO) {
        BookingDuration bookingDuration = convertToEntity(bookingDurationDTO);
        return convertToDTO(bookingDurationRepository.save(bookingDuration));
    }

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

    public void deleteBookingDuration(Long id) {
        bookingDurationRepository.deleteById(id);
    }

    private BookingDurationDTO convertToDTO(BookingDuration bookingDuration) {
        BookingDurationDTO bookingDurationDTO = new BookingDurationDTO();
        bookingDurationDTO.setId(bookingDuration.getId());
        bookingDurationDTO.setName(bookingDuration.getName());
        bookingDurationDTO.setDescription(bookingDuration.getDescription());
        return bookingDurationDTO;
    }

    private BookingDuration convertToEntity(BookingDurationDTO bookingDurationDTO) {
        BookingDuration bookingDuration = new BookingDuration();
        bookingDuration.setId(bookingDurationDTO.getId());
        bookingDuration.setName(bookingDurationDTO.getName());
        bookingDuration.setDescription(bookingDurationDTO.getDescription());
        return bookingDuration;
    }
}