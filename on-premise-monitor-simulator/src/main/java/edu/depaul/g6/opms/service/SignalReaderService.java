package edu.depaul.g6.opms.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignalReaderService {

    /*
     * Apply the signal captured from message broker.
     */
    public static void executeSignal(String signal) {
        MeterManager meterManager = MeterManager.getInstance();
        String[] meter = signal.split(":");
        final String macAddress = meter[0];
        final String command = meter[1];
        if(command.toLowerCase().equals("activate")) {
            meterManager.activateMeter(macAddress);
        } else if(command.toLowerCase().equals("deactivate")) {
            meterManager.deactivateMeter(macAddress);
        }
        log.info(String.format("Signal Received %s on %s.", command.toUpperCase(), macAddress));
    }
}
