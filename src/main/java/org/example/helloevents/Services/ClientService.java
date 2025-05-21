package org.example.helloevents.Services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.helloevents.DTO.ClientDto;
import org.example.helloevents.Models.Client;
import org.example.helloevents.Repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    public ClientDto addClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto,Client.class);
        Client savedClient = clientRepository.save(client);
        return modelMapper.map(savedClient, ClientDto.class);
    }
    public ClientDto getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return modelMapper.map(client, ClientDto.class);
    }

    public List<ClientDto> getAllClients() {
        List<Client> clients = (List<Client>) clientRepository.findAll();
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .toList();
    }

    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setName(clientDto.getName());
            client.setEmail(clientDto.getEmail());
            client.setPassword(clientDto.getPassword());
            Client updatedClient = clientRepository.save(client);
            return modelMapper.map(updatedClient, ClientDto.class);
        }
        return null;
    }
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
