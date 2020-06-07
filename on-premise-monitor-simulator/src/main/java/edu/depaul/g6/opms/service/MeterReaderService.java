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
    private final Receiver receiver;
    public MeterManager meterManager;

    @Autowired
    public MeterReaderService(Sender senderIn, Receiver receiverIn) {
        this.sender = senderIn;
        this.receiver = receiverIn;
        this.meterManager = MeterManager.getInstance();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterReaderService that = (MeterReaderService) o;
        return Objects.equals(sender, that.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender);
    }

    @Scheduled(fixedDelay = 2000)
    public void sendUsageData() {
        final String message = meterManager.getAllMeterData();
        sender.send(message);
    }
}
