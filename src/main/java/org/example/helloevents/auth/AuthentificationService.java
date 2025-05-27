package org.example.helloevents.auth;

import lombok.RequiredArgsConstructor;
import org.example.helloevents.Config.JwtService;
import org.example.helloevents.Models.Client;
import org.example.helloevents.Repositories.ClientRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {

        var user = Client.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);


        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}