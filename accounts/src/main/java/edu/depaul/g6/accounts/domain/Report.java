package edu.depaul.g6.accounts.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "outageReport")

public class Report {

    private Integer id;
    private Date outageDate;
    private String comment;
}
