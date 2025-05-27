package org.example.helloevents.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private String nomReservation;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateReservation;
    private String place;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idEvenement")
    private Event event;
}
