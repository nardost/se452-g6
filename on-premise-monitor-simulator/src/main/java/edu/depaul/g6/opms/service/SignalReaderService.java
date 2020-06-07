package edu.depaul.g6.opms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 *TODO:
 * This class is responsible for accepting
 * the signal from Reader and doing the
 * activation/deactivation.
 */

@Slf4j
public class SignalReaderService {

    public void doSomethingWithTheReceivedSignal(String signal) {
        String[] meter = signal.split(";");

        log.info("Activating Service: " + signal);
    }
}
