package edu.depaul.g6.billing.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Column(name = "billing_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate billingDate;


    /**
     * The date when the bill is due.
     */
    @Column(name = "due_date", nullable = false, columnDefinition = "DATE")
    private LocalDate dueDate;


    /**
     * The amount due.
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(6,2)")
    private BigDecimal amount;


    /**
     * The paid status of the bill (true if paid, false if unpaid).
     */
    @Column(name = "paid", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private Boolean paid;


    public boolean isOverdue() { return !paid && dueDate.isBefore(LocalDate.now()); }
    public String rowStyling() { return isOverdue() ? "overdue" : (paid ? "paid" : "unpaid"); }
}
