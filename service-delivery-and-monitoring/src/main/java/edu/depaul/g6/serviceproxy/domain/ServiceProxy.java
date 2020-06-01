package edu.depaul.g6.serviceproxy.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;


/**
 * A document which embeds all tracked meters and their respective usages.
 *
 * @author Christian Kleinvehn
 */
@Data
@AllArgsConstructor
@Document(collection = "meterUsage")
public class ServiceProxy {
    @Id
    private String id;
    List<Usage> usage;

    /*
        _id: (String),
        usage: [
            { from: (Timestamp), to: (Timestamp), kwhUsed: (Integer) },
            { from: (Timestamp), to: (Timestamp), kwhUsed: (Integer) },
            .
            .
            .
        ]
     */
}
