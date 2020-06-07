package edu.depaul.g6.opms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.connection.MessageListener;

@Service
@Slf4j
public class Receiver implements MessageListener {

    private SignalReaderService readerService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        /*
         *TODO
         * Receive signal and do something with.
         * (1) Extract mac address.
         * (2) Extract signal.
         * (3) Extract timestamp (when to apply the signal).
         * (4) Apply signal on mac address at timestamp (or immediately).
         */
        this.readerService = new SignalReaderService();
        readerService.implementSignal(message.toString());
        log.info(String.format("RECEIVED [%s] %s", new String(bytes), message));
    }
}
