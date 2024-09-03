package com.example.mulino.repository;

import com.example.mulino.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.reservationDate BETWEEN :startOfDay AND :endOfDay")
    List<Reservation> findAllByReservationDateRange(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT r FROM Reservation r WHERE r.table = :table AND r.reservationDate BETWEEN :startOfDay AND :endOfDay")
    List<Reservation> findAllByReservationDateRangeAndTable(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay, @Param("table") int table);


    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r WHERE r.reservationDate = :reservationDate AND r.table = :table")
    boolean existsByReservationDateAndTable(@Param("reservationDate") LocalDateTime reservationDate, @Param("table") int table);
}
