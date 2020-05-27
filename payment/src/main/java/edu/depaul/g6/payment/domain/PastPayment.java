package edu.depaul.g6.payment.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "payments")
public class PastPayment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payment_id;

    @Column(name = "customer_id")
    private Integer customer_id;

    @Column(name = "amount_paid")
    private Integer cents;

    @Column(name = "credit_card_num")
    private String card_num;
}
