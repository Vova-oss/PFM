package com.example.demo.Entity;

import com.example.demo.Security.SEntity.RefreshToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "pfm", name = "users")
public class UserEntity {

    @Id
    private Long id;

    private String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<TransactionData> transactionData;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

}
