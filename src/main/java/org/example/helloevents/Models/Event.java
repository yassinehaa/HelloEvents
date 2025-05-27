package org.example.helloevents.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idEvenement;
    private  String titre;
    private String type;
    private int nombrePlase;
    private String description;
    private  String dateDebut;
    private  String dateFin;
    @OneToMany(mappedBy = "event" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reservation> reservations;



}

