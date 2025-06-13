package org.example.helloevents.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto implements Serializable {
    private Long idReservation;
    private String nomReservation;
    private String description;
    private LocalDateTime dateReservation;
    private Integer place;
    private double prix;
    private Long idClient;
    private Long idEvenement;
}