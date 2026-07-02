package com.library.service;

import com.library.entity.Book;
import com.library.entity.Reservation;
import com.library.exception.BadRequestException;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.ReservationRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final CurrentUserService currentUserService;

    public ReservationService(ReservationRepository reservationRepository, BookRepository bookRepository,
                              CurrentUserService currentUserService) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.currentUserService = currentUserService;
    }

    @Transactional
    public Reservation reserve(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book.getAvailableQuantity() > 0) {
            throw new BadRequestException("Book is available. Please issue it directly.");
        }
        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(currentUserService.currentUser());
        return reservationRepository.save(reservation);
    }

    public List<Reservation> myReservations() {
        return reservationRepository.findByUser(currentUserService.currentUser());
    }

    public List<Reservation> allReservations() {
        return reservationRepository.findAll();
    }
}
