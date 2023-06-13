package com.example.jwt_auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.function.Function;

public class JwtService {

    private static final String SECRET_KEY = "zbRyDfRMVKtLYbGUzhvXaAUN8pso83oHdVvJ3LAH1d1A8xN9+73oOzfWV6EsrU00HO5/PeSaYtvlHBJDgKZoszZ5l1U0nITKtNvoh6vkZ0Sl6KKweqjLM+ZtlvjfyYUlT6zy53mKmQLg/ajZQKbtszb5E1r2Xn73SeCVmY+w4tsT2k0E9nUuO61lOZDOfL8mfIFDHgi7yjLhWHhUZgPXjdezmD6FJHOwTLBlinIumSFZaBjx8LkYL0+RBuH4mPEl0DscrnEJvs2TILESBKSaPa31T1ex5xgNq0fkrzUhD2cs1oyj+VSxCX9K1N7o/Tkue4cKf0JyIy04E+iyyTkTw95mmbPEioshU/FgFrTqNJk=\n";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
