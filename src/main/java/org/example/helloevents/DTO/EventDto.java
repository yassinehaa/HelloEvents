package org.example.helloevents.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.helloevents.Models.Event;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto implements Serializable {
    Long id;
    String titre;
    String lieu;
    LocalDate date;
    String categorie;
    int placesdisponible;
    String description;
}