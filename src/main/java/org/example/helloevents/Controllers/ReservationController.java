// ReservationController.java
package org.example.helloevents.Controllers;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.ReservationDto;
import org.example.helloevents.Services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private ReservationService reservationService;

    @PostMapping("/reserver")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ReservationDto> reserve(@RequestBody ReservationDto reservationDto) {
        ReservationDto saved = reservationService.reservation(reservationDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/getreservationbyid")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<List<ReservationDto>> getById(@RequestParam Long idClient) {
        List<ReservationDto> reservations = reservationService.findByClientId(idClient);
        return ResponseEntity.ok(reservations);
    }
}