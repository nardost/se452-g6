package edu.depaul.g6.opms.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MeterManager implements Serializable {

    List<Meter> meterList;

    private static final MeterManager instance = new MeterManager();

    public static synchronized MeterManager getInstance() {
        return instance;
    }

    //@Autowired
    private MeterManager(){this.meterList = new ArrayList<>();}

    public void addMeter(String macAddress, String powerUsage){
        Meter meterToAdd = new Meter(macAddress, powerUsage);
        this.meterList.add(meterToAdd);
    }

    public void printMeterList() {
        if(meterList.isEmpty()){ log.info("meterList is Empty");}
        else {
            for (Meter meter : meterList) {
                log.info(meter.macAddress + "\n");
            }
        }
    }

    public void activateMeter(String macAddress){
        Meter m = findByMacAddress(macAddress);
        if( m == Meter.NOT_FOUND)
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

    public String getAllMeterData()
    {
        StringBuilder allMeterData = new StringBuilder();

        for( Meter meter : meterList) {
            //log.info(String.format("METER\n"));
                allMeterData.append(meter.macAddress).append(",").append(meter.timeOfLastMeterReading).append(",").append(Timestamp.from(Instant.now()).toString()).append(",").append(meter.powerUsage).append(";");
                meter.timeOfLastMeterReading = Timestamp.from(Instant.now()).toString();
            if(meter.isActive()) {
                meter.powerUsage = Integer.toString(simulatedEnergyUsage());
            }
            else{
                meter.powerUsage =  "0";
            }
            printMeterList();
        }
        return allMeterData.toString();
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
