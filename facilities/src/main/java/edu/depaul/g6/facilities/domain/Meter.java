package edu.depaul.g6.facilities.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "smart_meters")
@Data
public class Meter {

    @Id
    private String id;

    private String macAddress;
    private String manufacturer;
    private String model;
}
