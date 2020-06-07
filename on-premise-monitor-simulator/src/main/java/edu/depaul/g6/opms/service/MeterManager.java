package edu.depaul.g6.opms.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MeterManager implements Serializable {

    /*
     * Should be a thread safe list.
     */
    private final List<Meter> meterList;

    private static MeterManager instance = null;

    /**
     * Make a singleton the proper way
     */
    public static MeterManager getInstance() {
        if(instance == null) {
            synchronized (MeterManager.class) {
                if(instance == null) {
                    instance = new MeterManager();
                }
            }
        }
        return instance;
    }

    private MeterManager() {
        /*
         * Thread safe list
         */
        this.meterList = Collections.synchronizedList(new ArrayList<>());
    }

    public void addMeter(String macAddress, String powerUsage) {
        Meter meterToAdd = new Meter(macAddress, powerUsage);
        this.meterList.add(meterToAdd);
    }

    public void activateMeter(String macAddress){
        Meter m = findByMacAddress(macAddress);
        if(m == Meter.NOT_FOUND)
        {
            addMeter(macAddress, Integer.toString(this.simulatedEnergyUsage()));
        }
        else {
            m.activate();
        }
    }

    public void deactivateMeter(String macAddress){
        Meter m = findByMacAddress(macAddress);
        if( m != Meter.NOT_FOUND)
        {
            m.deactivate();
        }
    }

    public Meter findByMacAddress(String macAddress){
        for(Meter meter : meterList){
            if(macAddress.equals(meter.macAddress)) {
                return meter;
            }
        }
        return Meter.NOT_FOUND;
    }

    public String getUsageDataOfAllActiveMeters() {

        StringBuilder allMeterData = new StringBuilder("");
        for(Meter meter : meterList) {
            if(meter.isActive()) {
                allMeterData
                        .append(meter.macAddress)
                        .append(",")
                        .append(meter.timeOfLastMeterReading)
                        .append(",")
                        .append(Timestamp.from(Instant.now()).toString())
                        .append(",")
                        .append(meter.powerUsage)
                        .append(";");
                meter.timeOfLastMeterReading = Timestamp.from(Instant.now()).toString();
                meter.powerUsage = Integer.toString(simulatedEnergyUsage());
            }
        }
        return allMeterData.toString();
    }

    /*
     * Generate usage data
     */
    public int simulatedEnergyUsage() {
        /*
         * 8e-5 kW per second
         * 16e-5 every 2 seconds
         */
        int usedEnergy = 0;
        Random rand = new Random();
        /*
         * Generate an integer between 10 and 20.
         */
        usedEnergy += rand.nextInt(10) + 10;
        return usedEnergy;
    }
}
