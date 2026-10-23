package com.example.identityms.repo;

import com.example.identityms.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Long> {
    public Optional<RefreshToken> findByToken(String token);
}
