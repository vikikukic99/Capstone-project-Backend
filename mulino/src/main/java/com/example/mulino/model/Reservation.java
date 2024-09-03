package com.example.mulino.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// za ocenjivanje da ima neki kod rezervacije koji korisnik unese i oceni
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="guest_name", nullable = false)
    private String guestName;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="number_of_people", nullable = false)
    private int numberOfPeople;

    @Column(name="reservation_date", nullable = false)
    private LocalDateTime reservationDate;

    @Column(name="reservation_table", nullable = false)
    private int table;
}
