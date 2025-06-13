// EventRepository.java
package org.example.helloevents.Repositories;

import org.example.helloevents.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByType(String type);
    List<Event> findEventByIdClient(Long id);
}