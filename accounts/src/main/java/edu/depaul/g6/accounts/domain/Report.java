package edu.depaul.g6.accounts.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = "outageReport")
public class Report {

    @Id
    private String outageDate;
    private String comment;
    private String streetAddress;
    private String city;
    private String state;
    private Integer zipCode;
    private Boolean activeOutage;
}
