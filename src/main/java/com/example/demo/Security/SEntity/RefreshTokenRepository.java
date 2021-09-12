package com.example.demo.Security.SEntity;

import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUserEntity(UserEntity userEntity);

}
