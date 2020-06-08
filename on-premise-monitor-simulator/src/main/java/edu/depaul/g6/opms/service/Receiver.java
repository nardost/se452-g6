package edu.depaul.g6.opms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.connection.MessageListener;
import static edu.depaul.g6.opms.service.SignalReaderService.executeSignal;

@Service
@Slf4j
public class Receiver implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info(String.format("RECEIVED FROM [%s] %s", new String(bytes), message));
        executeSignal(message.toString());
    }
}
