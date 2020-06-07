package edu.depaul.g6.opms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

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
    public MeterManager meterManager;

    @Autowired
    public MeterReaderService(Sender sender) {
        this.sender = sender;
        this.meterManager = MeterManager.getInstance();
    }

    @Scheduled(fixedDelay = 2000)
    public void sendUsageData() {
        final String message = meterManager.getUsageDataOfAllActiveMeters();
        if(message.length() > 0) {
            sender.send(message);
        }
    }
}
