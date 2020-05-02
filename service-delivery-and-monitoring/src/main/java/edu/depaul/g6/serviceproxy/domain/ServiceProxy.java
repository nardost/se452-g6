package edu.depaul.g6.serviceproxy.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;


@Data
@Document(collection = "utility_monitor")
public class ServiceProxy {
    @Id
    private String id;
    List<Usage> usage;

    /*
        id: (String),
        usage: [
            { timestamp: (ISODate), kwhUsed: (Integer) },
            { timestamp: (ISODate), kwhused: (Integer) },
            .
            .
            .
        ]
     */
}


/**
 * A document representing the accumulated kWh for the meter at a specific point in time.
 */
@Data
class Usage {
    private Date timestamp;
    private Integer kwhUsed;
}
