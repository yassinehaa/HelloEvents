// EventController.java
package org.example.helloevents.Controllers;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.EventDto;
import org.example.helloevents.Services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private EventService service;

    @PostMapping("/event")
    public ResponseEntity<EventDto> saveEvent(@RequestBody EventDto e) {
        EventDto saved = service.save(e);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> findAllEvenements() {
        List<EventDto> saved = service.findAll();
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> findEventById(@PathVariable Long id) {
        EventDto evenementDto = service.finById(id);
        return ResponseEntity.ok(evenementDto);
    }

    @PutMapping("/event/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto evenementDto) {
        EventDto update = service.update(id, evenementDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEvenement(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<EventDto>> findAllEvenementsByType(@PathVariable String type) {
        List<EventDto> saved = service.findEventByType(type);
        return ResponseEntity.ok(saved);
    }
}