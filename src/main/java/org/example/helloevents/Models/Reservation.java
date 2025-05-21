package org.example.helloevents.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime reservationDate;
    @ManyToOne
    @JoinColumn(name = "idClient")
    Client client;
    @ManyToOne
    @JoinColumn(name = "idEvenement")
    Event event;
}
