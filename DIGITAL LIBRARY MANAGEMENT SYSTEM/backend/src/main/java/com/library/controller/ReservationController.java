package com.library.controller;

import com.library.dto.ReservationRequest;
import com.library.entity.Reservation;
import com.library.service.ReservationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation reserve(@Valid @RequestBody ReservationRequest request) {
        return reservationService.reserve(request.bookId());
    }

    @GetMapping("/me")
    public List<Reservation> myReservations() {
        return reservationService.myReservations();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reservation> allReservations() {
        return reservationService.allReservations();
    }
}
