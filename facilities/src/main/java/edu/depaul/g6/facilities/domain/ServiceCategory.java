package edu.depaul.g6.facilities.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "service_categories")
public class ServiceCategory {
    @Id
    private String id;
    private String category;
    private double tariff;
}
