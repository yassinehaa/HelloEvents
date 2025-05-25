package org.example.helloevents.auth;

import lombok.RequiredArgsConstructor;
import org.example.helloevents.Config.JwtService;
import org.example.helloevents.Models.Client;
import org.example.helloevents.Repositories.ClientRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var client = Client.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(client);
        var jwtToken = jwtService.generateToken(client);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
    var client = repository.findClientByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(client);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
