package edu.depaul.g6.facilities.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "service_locations")
public class Location implements Serializable {

    @Id
    private Integer id;

    @Column(name = "street_address")
    @NotNull
    private String streetAddress;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "state")
    @NotNull
    private String state;

    @Column(name = "zip_code")
    @NotNull
    private int zipCode;

    @Column(name = "unit")
    private String unit;

    @NotNull
    @Column(name = "meter_mac_address", unique = true)
    private String meterMacAddress;
}
