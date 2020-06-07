package edu.depaul.g6.accounts.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;


@Data
@Document(collection = "outageReports")
public class Report {
    @Id
    private String id;
    private String accountId;
    private String outageDatetime;
    private String description;

    public Report(String accountId, String outageDatetime, String description) {
        this.accountId = accountId;
        this.outageDatetime = outageDatetime;
        this.description = description;
    }
}
