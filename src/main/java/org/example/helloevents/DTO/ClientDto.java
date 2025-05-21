package org.example.helloevents.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.helloevents.Models.Client;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto implements Serializable {
    Long id;
    String name;
    String email;
    String password;
}