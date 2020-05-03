package edu.depaul.g6.facilities.domain;

import edu.depaul.g6.facilities.ServiceStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "location_id")
    @NotNull
    private Integer locationId;

    @Column(name = "service_category")
    @NotNull
    private String serviceCategory;

    @Column(name = "activation_date_time")
    @NotNull
    private String activationDateTime;

    @Column(name = "service_status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ServiceStatus serviceStatus;
}

