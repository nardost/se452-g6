package edu.depaul.g6.opms.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MeterManager implements Serializable {

    List<Meter> meterList;

    public MeterManager(){this.meterList = new ArrayList<Meter>();}

    public void addMeter(String macAddress){
        Meter meterToAdd = new Meter(macAddress);
        this.meterList.add(meterToAdd);
    }

    public void printMeterList() {
        if(meterList.isEmpty()){ log.info(String.format("meterList is Empty"));}
        for(Meter meter : meterList)
        {
            log.info(String.format(meter.macAddress + "\n"));
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



    public void updateStatus(String macAddress, Meter.MeterStatus status){
        Meter m = findByMacAddress(macAddress);
        m.status = status;
    }








}
