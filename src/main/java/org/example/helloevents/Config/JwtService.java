package org.example.helloevents.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.DialectOverride;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private static final String secretkey="QXHzGw7uahd4Ah4LMlzRdKeZPPe+pia7fmleMxzhHPm9siCuy2aFNiwbuwXAt9k56Q/Zim7HhMk2+2S3LnZfSQopCpCLZl6rT8sPwwqwTdK5MjnRsuPNzswp8/jFszAVdD5ofCEfSdkDgQaiTTcwwvJ3dZ7N8msFdIwPCzXlzgCr1DOFv9LhcCruXaiL1ynemYEF8LwZVzGSbZhSO0BV4bnp+jTGipEbuWXgrNEFWDwMDCfSPse6H6w2UAy9fzK4CSBU9IqQHGbSkmGOAER5QZXyZy8ThDexjb7XcAT+6lzFYZVgzYnlEaM1hFtfMM4qAT8+f/oWhxW9+Gw1jOQ9h0WwWY+FpM8mmuYscyoGT9g=";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 24 minutes
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
