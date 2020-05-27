package edu.depaul.g6.facilities.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

@Data
@Document(collection = "serviceCategories")
public class ServiceCategory {
    @Id
    private String id;
    private String category;
    private double tariff;
}
