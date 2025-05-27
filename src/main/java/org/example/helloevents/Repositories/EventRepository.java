package org.example.helloevents.Repositories;

import org.example.helloevents.Models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event,Long> {
    List<Event> findEventByType(String type);
}
