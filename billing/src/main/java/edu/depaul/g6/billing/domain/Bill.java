package edu.depaul.g6.billing.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;


/**
 * This entity represents a bill of a customer.
 * <p>
 *     A bill entity has:
 *     <ul>
 *         <li>an ID (primary key)</li>
 *         <li>the associated utility's ID</li>
 *         <li>the date when it was sent out</li>
 *         <li>a flag recording its status (true if paid, false if unpaid)</li>
 *     </ul>
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
    private String utilityId;


    /**
     * The date when the bill was issued.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "billing_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date billingDate;


    /**
     * The paid status of the bill (true if paid, false if unpaid).
     */
    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean paidStatus;
}
