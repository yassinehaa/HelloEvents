package org.example.helloevents.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto implements Serializable {
    Long id;
    String username;
    String email;
    String password;
}