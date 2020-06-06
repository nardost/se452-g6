package edu.depaul.g6.opms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.Instant;
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
    private MeterManager meterManager;

    @Autowired
    public MeterReaderService(Sender sender) {
        this.sender = sender;
        this.meterManager = new MeterManager();
        this.meterManager.addMeter("01:23:45:67:89:ab");
        this.meterManager.addMeter("a1:23:45:67:89:ab");
        this.meterManager.addMeter("b1:23:45:67:89:ab");
        meterManager.printMeterList();
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
        /*
         *TODO
         * This message should be the usage data formatted in some way.
         * Example: mac-address:from-timestamp:to-timestamp:wattage
         */

        //final String message = Timestamp.from(Instant.now()).toString();


        //meterManager.printMeterList();
        final String message = meterManager.getAllMeterData();
        //meterManager.printMeterList();
        //if(message.isEmpty()){ log.info(String.format("message is Empty\n"));}
        sender.send(message);
    }
}
