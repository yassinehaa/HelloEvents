// ClientController.java
package org.example.helloevents.Controllers;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.ClientDto;
import org.example.helloevents.Services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {
    private ClientService clientService;

    @PostMapping("/client")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto) {
        ClientDto saved = clientService.save(clientDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/clients")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto clientDto = clientService.findById(id);
        return ResponseEntity.ok(clientDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        ClientDto saved = clientService.update(id, clientDto);
        return ResponseEntity.ok(saved);
    }
}