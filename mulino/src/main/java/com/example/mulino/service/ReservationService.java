package com.example.mulino.service;

import com.example.mulino.dto.ReservationDTO;
import com.example.mulino.model.Reservation;
import com.example.mulino.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmailService emailService;
    private Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }


    public Reservation makeReservation(ReservationDTO reservationDto) {

        if (reservationRepository.existsByReservationDateAndTable(reservationDto.getReservationDate(), reservationDto.getTable())) {
            return null;
        }
        Reservation reservation = new Reservation();

        reservation.setGuestName(reservationDto.getGuestName());
        reservation.setEmail(reservationDto.getEmail());
        reservation.setPhoneNumber(reservationDto.getPhoneNumber());
        reservation.setNumberOfPeople(reservationDto.getNumberOfPeople());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setTable(reservationDto.getTable());

        Reservation savedRes = reservationRepository.save(reservation);
        try{
            emailService.sendReservationConfirmation(reservation);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return savedRes;
    }

    public List<Reservation> getReservationsForDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return reservationRepository.findAllByReservationDateRange(startOfDay, endOfDay);
    }


    private boolean checkIfTimeAvailable(LocalTime time, List<Reservation> reservations){
        for(Reservation reservation : reservations){

            if(reservation.getReservationDate().toLocalTime().equals(time)){
                return false;
            }
        }
        return true;
    }
    public List<String> getAvailableTimes(LocalDate date, int table){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<Reservation> reservations = reservationRepository.findAllByReservationDateRangeAndTable(startOfDay, endOfDay, table);

        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(21, 0);
        Duration step = Duration.ofMinutes(30);

        LocalTime currentTime = startTime;

        List<String> availableTimes = new ArrayList<String>();

        while (!currentTime.isAfter(endTime)) {

            if(checkIfTimeAvailable(currentTime, reservations)){
                availableTimes.add(currentTime.toString());
            }

            currentTime = currentTime.plus(step);
        }
        return availableTimes;

    }


}
