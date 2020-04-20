package edu.depaul.g6.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ServiceCategory {
    @Id
    private String id;
    private String description;
    private double tariff;
}
