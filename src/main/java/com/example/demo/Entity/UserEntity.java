package com.example.demo.Entity;

import com.example.demo.Security.SEntity.RefreshToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pfm_user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

    @OneToMany(mappedBy = "client")
    private List<TransactionData> transactionData;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pfm_roles_user_entities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @OneToOne(mappedBy = "userEntity")
    private RefreshToken refreshToken;

}
