package com.example.demo.Security.SEntity;

import com.example.demo.Entity.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "pfm_refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false, name = "expiry_date")
    private Long expiryDate;

}
