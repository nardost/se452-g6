package edu.depaul.g6.facilities.domain;

import edu.depaul.g6.facilities.ServiceStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location")
    private Location location;

    @Column(name = "service_category")
    @NotNull
    private String serviceCategory;

    @Column(name = "activation_timestamp")
    @NotNull
    private Timestamp activationTimeStamp;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "service_status")
    private ServiceStatus serviceStatus;
}
