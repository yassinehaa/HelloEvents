// ReservationService.java
package org.example.helloevents.Services;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.ReservationDto;
import org.example.helloevents.Models.Client;
import org.example.helloevents.Models.Event;
import org.example.helloevents.Models.Reservation;
import org.example.helloevents.Repositories.ClientRepository;
import org.example.helloevents.Repositories.EventRepository;
import org.example.helloevents.Repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public ReservationDto reservation(ReservationDto reservationDto) {
        Client client = clientRepository.findById(reservationDto.getIdClient())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Event event = eventRepository.findById(reservationDto.getIdEvenement())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        long currentReservations = reservationRepository.countByEvent(event);
        if (event.getNombrePlase() <= currentReservations) {
            throw new RuntimeException("No places available");
        }

        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservation.setClient(client);
        reservation.setEvent(event);
        Reservation saved = reservationRepository.save(reservation);
        ReservationDto dto = modelMapper.map(saved, ReservationDto.class);
        dto.setIdClient(client.getIdClient());
        dto.setIdEvenement(event.getIdEvenement());
        return dto;
    }

    public List<ReservationDto> findByClientId(Long idClient) {
        return reservationRepository.findByClient_IdClient(idClient).stream()
                .map(reservation -> {
                    ReservationDto dto = modelMapper.map(reservation, ReservationDto.class);
                    dto.setIdClient(reservation.getClient().getIdClient());
                    dto.setIdEvenement(reservation.getEvent().getIdEvenement());
                    return dto;
                })
                .toList();
    }
}