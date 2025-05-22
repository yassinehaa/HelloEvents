package org.example.helloevents.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    private String titre;
    private String lieu;
    private LocalDate date;
    private String categorie;
    private int placesdisponible;
    private String description;
    @OneToMany(mappedBy = "event")
    private List<Reservation> reservation;
}
