package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "pfm", name = "mcc")
public class MCC {

    @Id
    @Column(name = "code")
    private Long code;

    @Column(name = "info")
    private String info;

    @Column(name = "group_code")
    private String group_code;

    @OneToOne(mappedBy = "mcc_code")
    private TransactionData transactionData;
}
