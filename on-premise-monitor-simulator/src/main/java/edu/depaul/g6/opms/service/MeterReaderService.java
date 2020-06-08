package edu.depaul.g6.opms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nardos
 *TODO:
 * This class will generate the meter reading
 * for each mac-address and send the reading
 * to the Redis message broker on channel "meter-reading".
 */
@Service
@Slf4j
public class MeterReaderService {

    private final Sender sender;
    private final MeterManager meterManager;
    private final static long PUBLISHING_FREQUENCY = 5000L;

    @Autowired
    public MeterReaderService(Sender sender) {
        this.sender = sender;
        this.meterManager = MeterManager.getInstance();
    }

    /*
     * Publishes usage data to message broker every
     * few seconds for demonstration only.
     * In reality, the frequency would be lower than this,
     * like once every hour or so.
     */
    @Scheduled(fixedDelay = PUBLISHING_FREQUENCY)
    public void sendUsageData() {
        final String message = meterManager.getUsageDataOfAllActiveMeters();
        if(message.length() > 0) {
            sender.send(message);
        }
    }
}
