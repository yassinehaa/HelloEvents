package org.example.helloevents.Services;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.ReservationDto;
import org.example.helloevents.Models.Reservation;
import org.example.helloevents.Repositories.ClientRepository;
import org.example.helloevents.Repositories.EventRepository;
import org.example.helloevents.Repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;



}
