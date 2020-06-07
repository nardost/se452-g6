package edu.depaul.g6.opms.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MeterManager implements Serializable {

    List<Meter> meterList;

    private static MeterManager instance = new MeterManager();

    public static MeterManager getInstance() {
        return instance;
    }

    @Autowired
    private MeterManager(){this.meterList = new ArrayList<Meter>();}

    public void addMeter(String macAddress, String powerUsage){
        Meter meterToAdd = new Meter(macAddress, powerUsage);
        this.meterList.add(meterToAdd);
    }

    public void printMeterList() {
        if(meterList.isEmpty()){ log.info(String.format("meterList is Empty"));}
        for(Meter meter : meterList)
        {
            log.info(String.format(meter.macAddress + "\n"));
        }
    }

    public void activateMeter(String macAddress){
        Meter m = findByMacAddress(macAddress);
        if( m == Meter.NOT_FOUND)
        {
            addMeter(macAddress, Integer.toString(this.simulatedEnergyUsage()));
        }
        else {
            m.Activate();
        }
    }

    public void deactivateMeter(String macAddress){
        Meter m = findByMacAddress(macAddress);
        if( m != Meter.NOT_FOUND)
        {
            m.Deactivate();
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

    public String getAllMeterData()
    {
        String allMeterData = new String();

        for( Meter meter : meterList) {
            //log.info(String.format("METER\n"));
            allMeterData += meter.macAddress + "," + meter.timeOfLastMeterReading + "," + Timestamp.from(Instant.now()).toString() + "," + meter.powerUsage + ";";
            //log.info(String.format("%s\n", allMeterData));
        }
        return allMeterData;
    }

    public int simulatedEnergyUsage() {
        int usedEnergy = 0;
        Random rand = new Random();
        usedEnergy += rand.nextInt(20) * 50;
        return usedEnergy;
    }

    public void updateStatus(String macAddress, Meter.MeterStatus status){
        Meter m = findByMacAddress(macAddress);
        m.status = status;
    }








}
