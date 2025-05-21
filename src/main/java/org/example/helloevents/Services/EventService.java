package org.example.helloevents.Services;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.EventDto;
import org.example.helloevents.Models.Event;
import org.example.helloevents.Repositories.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventDto addEvent(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        Event savedEvent = eventRepository.save(event);
        return modelMapper.map(savedEvent, EventDto.class);
    }
    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        return modelMapper.map(event, EventDto.class);
    }
    public List<EventDto> getAllEvents() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        return events.stream()
                .map(event -> modelMapper.map(event, EventDto.class))
                .toList();
    }
    public EventDto updateEvent(Long id, EventDto eventDto) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setTitre(eventDto.getTitre());
            event.setLieu(eventDto.getLieu());
            event.setDate(eventDto.getDate());
            event.setCategorie(eventDto.getCategorie());
            event.setPlacesdisponible(eventDto.getPlacesdisponible());
            event.setDescription(eventDto.getDescription());
            Event updatedEvent = eventRepository.save(event);
            return modelMapper.map(updatedEvent, EventDto.class);
        }
        return null;
    }
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
