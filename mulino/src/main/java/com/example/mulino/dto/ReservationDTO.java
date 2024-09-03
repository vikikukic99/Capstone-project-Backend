package com.example.mulino.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {
    private Long id;
    private String guestName;
    private String email;
    private String phoneNumber;
    private int numberOfPeople;
    private LocalDateTime reservationDate;
    private int table;

}
