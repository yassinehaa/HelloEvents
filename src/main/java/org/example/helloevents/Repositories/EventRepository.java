package org.example.helloevents.Repositories;

import org.example.helloevents.Models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event,Long> {
}
