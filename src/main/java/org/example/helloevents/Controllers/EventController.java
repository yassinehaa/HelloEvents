package org.example.helloevents.Controllers;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.EventDto;
import org.example.helloevents.Services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/add")
    private EventDto addEvent(EventDto eventDto) {
        return eventService.addEvent(eventDto);
    }

    @GetMapping("/{id}")
    private EventDto getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/all")
    private List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PutMapping("/{id}")
    private EventDto updateEvent(Long id, EventDto eventDto) {
        return eventService.updateEvent(id, eventDto);
    }

    @DeleteMapping("/{id}")
    private Void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return null;
    }
}
