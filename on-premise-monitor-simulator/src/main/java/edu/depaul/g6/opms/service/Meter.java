package edu.depaul.g6.opms.service;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;


@Component
public class Meter implements Serializable {

    public static Meter NOT_FOUND = new Meter();

    public enum MeterStatus {
        ACTIVE,
        INACTIVE
    }

    //@Autowired // Getting Error with @Autowired
    public String macAddress;
    //@Autowired // Getting Error with @Autowired
    public String timeOfLastMeterReading;
    //@Autowired // Getting Error with @Autowired
    public String powerUsage;
    //@Autowired // Getting Error with @Autowired\
    public MeterStatus status;

    public Meter(){this.macAddress = "00:00:00:00:00:00"; this.timeOfLastMeterReading = Timestamp.from(Instant.now()).toString(); this.powerUsage = "000000000000"; status = MeterStatus.ACTIVE;}
    public Meter(String macAddressIn, String powerUsage){this.macAddress = macAddressIn; this.timeOfLastMeterReading = Timestamp.from(Instant.now()).toString(); this.powerUsage = powerUsage; status = MeterStatus.ACTIVE;}

    public void activate() {this.status = MeterStatus.ACTIVE;}
    public void deactivate() {this.status = MeterStatus.INACTIVE;}
    public boolean isActive() {return this.status == MeterStatus.ACTIVE;}
}
