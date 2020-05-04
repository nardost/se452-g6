package edu.depaul.g6.serviceproxy.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;


/**
 * A document which embeds all tracked meters and their respective usages.
 *
 * @author Christian Kleinvehn
 */
@Data
@Document(collection = "meter_usage")
public class ServiceProxy {
    @Id
    private String id;
    List<Usage> usage;

    /*
        _id: (String),
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
 * A document representing the accumulated kWh for a meter at a specific point in time.
 *
 * @author Christian Kleinvehn
 */
@Data
class Usage {
    private Date timestamp;
    private Integer kwhUsed;
}
