package edu.depaul.g6.delivery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivationNotifier {

    private final Sender sender;

    @Autowired
    public ActivationNotifier(Sender sender) {
        this.sender = sender;
    }

    /*
     * This is not a scheduled job. It is
     * called when an activation request comes.
     */
    public void sendSignal(final String macAddress, final String signal) {
        /*
         * Format the signal ... Add timestamp etc...
         */
        final String message = macAddress + ":" + signal;
        sender.send(message);
        log.info("Sending signal " + signal);
    }
}
