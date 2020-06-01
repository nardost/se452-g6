package edu.depaul.g6.billing.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;


/**
 * This entity represents the bill of a customer.
 *
 * @author Christian Kleinvehn
 */
@Entity
@Data
@Table(name = "bills")
public class Bill implements Serializable {
    /**
     * The auto-generated primary key, confers no semantic meaning.
     */
    @Id                                                 // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity column
    private Long id;


    /**
     * The corresponding utility associated with the bill.
     */
    @Column(name = "account_number", nullable = false)
    private String accountNumber;


    /**
     * The date when the bill was issued.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "billing_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date billingDate;


    /**
     * The date when the bill is due.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "due_date", nullable = false)
    private Date dueDate;


    /**
     * The amount due.
     */
    @Column(name = "amount", nullable = false)
    private Integer amount;


    /**
     * The paid status of the bill (true if paid, false if unpaid).
     */
    @Column(name = "paid", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private Boolean paid;
}
