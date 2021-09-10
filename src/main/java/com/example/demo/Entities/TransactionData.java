package com.example.demo.Entities;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pfm_transaction_data")
public class TransactionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity client;

    private Long card_id;
    private String score_of_card;
    private String date;
    private String sum;
    private String currency;
    private String info;
    private Integer mcc_code;
    private String mcc_info;

}
