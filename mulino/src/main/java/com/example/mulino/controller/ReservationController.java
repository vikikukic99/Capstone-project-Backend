package com.example.mulino.controller;

import com.example.mulino.dto.ReservationDTO;
import com.example.mulino.model.Reservation;
import com.example.mulino.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationController {
    @Autowired
    ReservationService reservationService;


    @PostMapping("")
    public ResponseEntity<Reservation> makeReservation(@RequestBody ReservationDTO reservationDto) {
        Reservation reservation = reservationService.makeReservation(reservationDto);
        if (reservation == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/reservationsForDate")
    public List<Reservation> getReservationsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Reservation> reservations = reservationService.getReservationsForDate(date);
        return reservations;
    }

    @GetMapping("/availableTimes")
    public List<String> getAvailableTimes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam int table) {

        return reservationService.getAvailableTimes(date, table);
    }
}
