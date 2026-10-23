package com.example.identityms.service.impl;


import com.example.identityms.model.RefreshToken;
import com.example.identityms.model.User;
import com.example.identityms.payload.request.RefreshTokenRequest;
import com.example.identityms.payload.response.RefreshTokenResponse;
import com.example.identityms.repo.RefreshTokenRepo;
import com.example.identityms.repo.UserRepo;
import com.example.identityms.service.IRefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RefreshTokenService implements IRefreshTokenService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private JwtService jwtService;

    @Value("${spring.jwt.refresh-expiration}")
    private long refreshTokenExpiration;

    @Override
    public RefreshToken generateRefreshToken(Long id) {
        User user  = userRepo.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        RefreshToken refreshToken = RefreshToken.builder()
                .token(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
                .user(user)
                .expiredDate(Instant.now().plusMillis(refreshTokenExpiration))
                .revoked(true)
                .build();
        return refreshTokenRepo.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public RefreshTokenResponse generateNewRefreshToken(RefreshTokenRequest request) {
        User user = refreshTokenRepo.findByToken(request.getRefreshToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .orElseThrow(()-> new RuntimeException("user not found"));
        String token = jwtService.generateToken(user);
        return RefreshTokenResponse.builder()
                .accessToken(token)
                .refreshToken(request.getRefreshToken())
                .tokenType("BEARER")
                .build();
    }

    @Override
    public void deleteByToken(String token) {
        refreshTokenRepo.findByToken(token).ifPresent(refreshTokenRepo::delete);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token)  {
        if(token == null) {
            log.error("token is null");
            throw new RuntimeException("Token is null");
        }
        if(token.getExpiredDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new RuntimeException("token is expired");
        }
        return token;
    }
}
