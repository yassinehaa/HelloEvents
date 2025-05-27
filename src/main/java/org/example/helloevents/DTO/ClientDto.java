package org.example.helloevents.DTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto implements Serializable {
    Long idClient;
    String nom;
    String email;
    String password;
}