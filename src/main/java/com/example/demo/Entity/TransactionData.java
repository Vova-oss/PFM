package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "pfm", name = "transaction_data")
public class TransactionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long card_id;
    private String score_of_card;
    private String date;
    private String sum;
    private String currency;
    private String info;
    private String mcc_info;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mcc_code")
    private MCC mcc_code;

}
