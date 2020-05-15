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
@Table(name = "subscriber")
public class Subscriber implements Serializable {

    @Id
    private String id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "street_address")
    @NotNull
    private String streetAddress;

    @Column(name = "unit")
    private String unit;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "state")
    @NotNull
    private String state;

    @Column(name = "zip_code")
    @NotNull
    private int zipCode;

    @Column(name = "credit_card_number")
    @NotNull
    private String creditCardNumber;

    @Column(name = "cvv")
    @NotNull
    private String cvv;

    @Column(name = "expiration_mm")
    @NotNull
    private String mm;

    @Column(name = "expiration_yyyy")
    @NotNull
    private String yyyy;

    @Column(name = "service_type")
    @NotNull
    private String serviceType;
}
