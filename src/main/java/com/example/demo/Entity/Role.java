package com.example.demo.Entity;

import com.example.demo.Entity.Enum.ERoles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pfm_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ERoles role;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> userEntities;

}
