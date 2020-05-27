package edu.depaul.g6.delivery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.connection.MessageListener;

@Service
@Slf4j
public class Receiver implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        /*
        * TODO: Get a service method that persists usage data and persist it.
         * Receive usage data.
         * (1) Extract mac address.
         * (2) Extract from-to timestamp info.
         * (3) Extract wattage value.
         * (4) Persist in Usage database.
         */
        log.info(String.format("RECEIVED [%s] %s", new String(bytes), message));
    }
}
