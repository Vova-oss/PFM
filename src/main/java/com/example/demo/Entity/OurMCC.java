package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pfm_our_mcc")
public class OurMCC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;

    @Column(name = "info")
    private String info;

    @Column(name = "group_code")
    private String group_code;

    @Column(name = "group_code_rus")
    private String group_code_rus;

    @OneToOne(mappedBy = "ourMCC")
    private TransactionData transactionData;

}
