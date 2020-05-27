package edu.depaul.g6.opms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author nardos
 *TODO:
 * This class will generate the meter reading
 * for each mac-address and send the reading
 * to the Redis message broker on channel "meter-reading".
 */
@Service
public class MeterReaderService {

    private final Sender sender;

    @Autowired
    public MeterReaderService(Sender sender) {
        this.sender = sender;
    }

    @Scheduled(fixedDelay = 2000)
    public void sendUsageData() {
        /*
         *TODO
         * This message should be the usage data formatted in some way.
         * Example: mac-address:from-timestamp:to-timestamp:wattage
         */
        final String message = Timestamp.from(Instant.now()).toString();
        sender.send(message);
    }
}
