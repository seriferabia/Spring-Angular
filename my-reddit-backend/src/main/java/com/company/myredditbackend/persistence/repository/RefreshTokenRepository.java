package com.company.myredditbackend.persistence.repository;


import com.company.myredditbackend.persistence.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
