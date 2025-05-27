package org.example.helloevents.Services;

import org.example.helloevents.DTO.ReservationDto;
import org.example.helloevents.Models.Client;
import org.example.helloevents.Models.Event;
import org.example.helloevents.Models.Reservation;
import org.example.helloevents.Repositories.ClientRepository;
import org.example.helloevents.Repositories.EventRepository;
import org.example.helloevents.Repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class ReservationService {
    public ReservationService(ReservationRepository resrvationRepository, ClientRepository clientRepository, EventRepository evenementRepository, ModelMapper modelMapper) {
        this.resrvationRepository = resrvationRepository;
        this.clientRepository = clientRepository;
        this.evenementRepository = evenementRepository;
        this.modelMapper = modelMapper;
    }

    private final ReservationRepository resrvationRepository;
    private final ClientRepository clientRepository;
    private final EventRepository evenementRepository;
    private ModelMapper modelMapper;
    public ReservationDto reservation(ReservationDto resrvationDto) {
        Client client=clientRepository.findById(resrvationDto.getIdClient())
                .orElseThrow(()-> new RuntimeException("Client not found"));
        Event evenement=evenementRepository.findById(resrvationDto.getIdEvenement())
                .orElseThrow(()-> new RuntimeException("Event not found"));
        Reservation resrvation=modelMapper.map(resrvationDto, Reservation.class);
        resrvation.setClient(client);
        resrvation.setEvent(evenement);
        Reservation rserver=resrvationRepository.save(resrvation);
        ReservationDto dto=modelMapper.map(rserver, ReservationDto.class);
        dto.setIdClient(resrvationDto.getIdClient());
        dto.setIdEvenement(resrvationDto.getIdEvenement());
        return dto;
    }

}
