package org.example.helloevents.Repositories;

import org.example.helloevents.Models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client,Long> {
    Client findClientByEmail(String email);
}
