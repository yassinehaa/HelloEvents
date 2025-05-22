package org.example.helloevents.Controllers;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.ClientDto;
import org.example.helloevents.Services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    @PostMapping
    private ClientDto addClient(ClientDto clientDto) {
        return clientService.addClient(clientDto);
    }
    @GetMapping
    private ClientDto getClientById(Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping
    private List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }
    @PutMapping
    private ClientDto updateClient(Long id, ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }
    @DeleteMapping
    private Void deleteClient(Long id) {
        clientService.deleteClient(id);
        return null;
    }

}
