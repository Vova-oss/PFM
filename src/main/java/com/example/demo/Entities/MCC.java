package com.example.demo.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pfm_mcc")
public class MCC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;

    @Column(name = "info")
    private String info;

    @Column(name = "group_code")
    private String group_code;

    @OneToOne(mappedBy = "mcc_code")
    private TransactionData transactionData;
}
