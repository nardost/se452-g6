package edu.depaul.g6.accounts.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = "outageReport")
public class Report {

    @Id
    private Integer id;
    private Date outageDate;
    private String comment;
}
