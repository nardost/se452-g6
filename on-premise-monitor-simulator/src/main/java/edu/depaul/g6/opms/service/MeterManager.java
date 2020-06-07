package edu.depaul.g6.opms.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MeterManager implements Serializable {

    List<Meter> meterList;

    public void MeterManager(){this.meterList = new ArrayList<Meter>();}

    public void addMeter(Meter meterToAdd){
        this.meterList.add(meterToAdd);
    }

    public Meter findByMacAddress(String macAddress){
        for(Meter meter : meterList){
            if(macAddress.equals(meter.macAddress)) {
                return meter;
            }
        }
        return Meter.NOT_FOUND;
    }


    public void updateStatus(String macAddress, Meter.meterStatus status){
        Meter m = findByMacAddress(macAddress);
        m.status = status;
    }








}
