package org.example.helloevents.Repositories;

import org.example.helloevents.Models.Client;
import org.springframework.data.repository.CrudRepository;

import java.lang.ScopedValue;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client,Long> {

    Optional<Client> findByEmail(String email);

}
