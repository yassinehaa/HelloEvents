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
    private  final EventRepository eventRepository;
    private ModelMapper modelMapper;
    public EventDto save( EventDto evenementDto) {
        Event evenement= modelMapper.map(evenementDto,Event.class);
        Event saved =eventRepository.save(evenement);
        return modelMapper.map(saved,EventDto.class);
    }
    public List<EventDto> findAll() {
        List<Event> evenements= (List<Event>) eventRepository.findAll();
        return evenements.stream()
                .map(evenement -> modelMapper.map(evenement,EventDto.class))
                .toList();
    }
    public EventDto finById( Long id) {
        Event evenement=eventRepository.findById(id).get();
        return modelMapper.map(evenement,EventDto.class);
    }
    public EventDto update( Long id,  EventDto evenementDto) {
        Event evenement=eventRepository.findById(id).get();
        evenement.setDescription(evenementDto.getDescription());
        evenement.setTitre(evenementDto.getTitre());
        evenement.setType(evenementDto.getType());
        evenement.setNombrePlase(evenementDto.getNombrePlase());
        evenement.setDateFin(evenementDto.getDateFin());
        evenement.setDateDebut(evenementDto.getDateDebut());
        Event update=eventRepository.save(evenement);
        return modelMapper.map(update,EventDto.class);
    }
    public void delete( Long id) {
        eventRepository.deleteById(id);
    }
    public List<EventDto> findEventByType(String type) {
        List<Event> evenements=eventRepository.findEventByType((type));
        return   evenements.stream()
                .map(evenement -> modelMapper.map(evenement,EventDto.class))
                .toList();

    }


}