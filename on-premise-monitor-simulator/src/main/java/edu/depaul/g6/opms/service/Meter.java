package edu.depaul.g6.opms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;



@Component
public class Meter implements Serializable {

    public static Meter NOT_FOUND = new Meter();

    public enum meterStatus {
        ACTIVE,
        UNACTIVE
    }

    //@Autowired // Getting Error with @Autowired
    public String macAddress;
    //@Autowired // Getting Error with @Autowired
    public String powerUsage;
    //@Autowired // Getting Error with @Autowired\
    public meterStatus status;

    public void Meter(){this.macAddress = "00:00:00:00:00:00"; this.powerUsage = "000000000000";}
    public void Meter(String macAddressIn, String powerUsageIn){this.macAddress = macAddressIn; this.powerUsage = powerUsageIn;}


}
