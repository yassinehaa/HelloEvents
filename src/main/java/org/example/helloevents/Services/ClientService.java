// ClientService.java
package org.example.helloevents.Services;

import lombok.AllArgsConstructor;
import org.example.helloevents.DTO.ClientDto;
import org.example.helloevents.Models.Client;
import org.example.helloevents.Repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientDto save(ClientDto clientDto) {
        Client client = Client.builder()
                .nom(clientDto.getNom())
                .email(clientDto.getEmail())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .role("CLIENT")
                .build();
        Client saved = clientRepository.save(client);
        return new ClientDto(saved.getIdClient(), saved.getNom(), saved.getEmail(), null);
    }

    public List<ClientDto> findAll() {
        return ((List<Client>) clientRepository.findAll()).stream()
                .map(client -> new ClientDto(client.getIdClient(), client.getNom(), client.getEmail(), null))
                .collect(Collectors.toList());

    }

    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return new ClientDto(client.getIdClient(), client.getNom(), client.getEmail(), null);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientDto update(Long id, ClientDto clientDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setNom(clientDto.getNom());
        client.setEmail(clientDto.getEmail());
        if (clientDto.getPassword() != null && !clientDto.getPassword().isEmpty()) {
            client.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        }
        Client updated = clientRepository.save(client);
        return new ClientDto(updated.getIdClient(), updated.getNom(), updated.getEmail(), null);
    }
}