package com.example.identityms.service.impl;

import com.example.identityms.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class JwtService implements IJwtService {
    @Value("${spring.jwt.key}")
    private String secretKey;
    @Value("${spring.jwt.access-expiration}")
    private long jwtExpiration;




    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),  userDetails);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildToken(claims, userDetails,jwtExpiration);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        return extractUsername(token).equals(username) & !isTokenExpired(token);
    }

    @Override
    public ResponseCookie generateJwtCookie(String jwt) {
        return null;
    }

    @Override
    public String getJwtFromCookies(HttpServletRequest request) {
        return "";
    }

    @Override
    public ResponseCookie getCleanJwtCookie() {
        return null;
    }




    public String buildToken(Map<String, Object> claims, UserDetails userDetails, long expiration) {
        return
                Jwts.builder()
                        .claims(claims)
                        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                        .subject(userDetails.getUsername())
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + expiration))
                        .compact();
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
