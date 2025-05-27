package org.example.helloevents.Controllers;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.ReservationDto;
import org.example.helloevents.Services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;
    @PostMapping("reserver")
    private ResponseEntity reserve(@RequestBody ReservationDto resrvationDto) {
        ReservationDto resrvationDto1=reservationService.reservation(resrvationDto);
        return ResponseEntity.ok().body(resrvationDto1);

    }
}
