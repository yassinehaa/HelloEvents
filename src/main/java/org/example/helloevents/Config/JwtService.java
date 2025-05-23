package org.example.helloevents.Config;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String extractUsername(String token) {
        return null;
    }

    public boolean isTokenExpired(String token) {
        return false;
    }

    public String generateToken(String username) {
        return null;
    }

    public boolean validateToken(String token, String username) {
        return false;
    }
}
