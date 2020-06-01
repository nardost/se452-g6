package edu.depaul.g6.facilities.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "smartMeters")
public class Meter {
    @Id
    private String id;
    private String mac;
    private String model;
    private String manufacturer;
    private boolean installed;
}
