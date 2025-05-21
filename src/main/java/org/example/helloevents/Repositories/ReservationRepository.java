package org.example.helloevents.Repositories;

import org.example.helloevents.Models.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}
