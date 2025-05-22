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
        Event event = eventRepository.findById(reservationDto.getIdEvent()).orElseThrow(() ->
                new RuntimeException("Event not found"));
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservation.setClient(client);
        reservation.setEvent(event);
        Reservation savedReservation = reservationRepository.save(reservation);
        ReservationDto reservationDto1 = modelMapper.map(savedReservation,ReservationDto.class);
        reservationDto1.setIdClient(reservationDto.getIdClient());
        reservationDto1.setIdEvent(reservationDto.getIdEvent());
        return reservationDto1;


    }
}
