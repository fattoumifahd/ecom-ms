package com.example.identityms.service;

import com.example.identityms.model.RefreshToken;
import com.example.identityms.payload.request.RefreshTokenRequest;
import com.example.identityms.payload.response.RefreshTokenResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IRefreshTokenService {
    public RefreshToken generateRefreshToken(Long id);
    public Optional<RefreshToken> findByToken(String token);
    public RefreshTokenResponse generateNewRefreshToken(RefreshTokenRequest request);
    public void deleteByToken(String token);
    public RefreshToken verifyExpiration(RefreshToken token);




}
