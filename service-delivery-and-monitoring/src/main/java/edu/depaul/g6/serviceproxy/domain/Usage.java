package edu.depaul.g6.serviceproxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * A document representing the kWh used for a meter over a period of time.
 *
 * @author Christian Kleinvehn
 */
@Data
@AllArgsConstructor
public class Usage {
    private String from;
    private String to;
    private Integer kwhUsed;
}
