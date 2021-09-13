package com.example.demo.Repository;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Security.SEntity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUser(UserEntity user);

}
