package edu.depaul.g6.opms.service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SignalReaderService {


    public void implementSignal(String signal) {
        MeterManager meterManager = MeterManager.getInstance();
        String[] meter = signal.split(":");
        if(meter[1].equals("activate")){ meterManager.activateMeter(meter[0]);}
        else if(meter[1].equals("deactivate")){ meterManager.deactivateMeter(meter[0]);}
        log.info("Activating Service: " + signal);
    }
}
