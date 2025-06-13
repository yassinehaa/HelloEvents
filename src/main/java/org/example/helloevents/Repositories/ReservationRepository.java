// ReservationRepository.java
package org.example.helloevents.Repositories;

import org.example.helloevents.Models.Event;
import org.example.helloevents.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
 long countByEvent(Event event);
 List<Reservation> findByClient_IdClient(Long idClient);
}