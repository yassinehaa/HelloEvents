package org.example.helloevents.Controllers;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.EventDto;
import org.example.helloevents.Services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    private EventDto addEvent(EventDto eventDto) {
        return eventService.addEvent(eventDto);
    }

    @GetMapping
    private EventDto getEventById(Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    private List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PutMapping
    private EventDto updateEvent(Long id, EventDto eventDto) {
        return eventService.updateEvent(id, eventDto);
    }

    @DeleteMapping
    private Void deleteEvent(Long id) {
        eventService.deleteEvent(id);
        return null;
    }
}
