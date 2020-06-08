package edu.depaul.g6.delivery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.connection.MessageListener;

import lombok.extern.slf4j.Slf4j;

import edu.depaul.g6.serviceproxy.domain.ServiceProxy;
import edu.depaul.g6.serviceproxy.domain.Usage;
import edu.depaul.g6.serviceproxy.repository.ServiceProxyRepository;


@Service
@Slf4j
public class Receiver implements MessageListener {
    private final ServiceProxyRepository repo;

    @Autowired
    Receiver(ServiceProxyRepository repo){
        this.repo = repo;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) { // pattern is whatever matched the channel

        log.info(String.format("RECEIVED FROM [%s] %s", new String(message.getChannel()), message));

        String[] meters = new String(message.getBody()).split(";");

        for (String meter : meters) {
            String[] components = meter.split(",");

            String macAddress = components[0];
            String from       = components[1];
            String to         = components[2];
            Integer kwhUsed   = Integer.parseInt(components[3]);

            if (repo.existsById(macAddress)) { // has a history
                ServiceProxy document = repo.findById(macAddress).get();
                document.getUsage().add(new Usage(from, to, kwhUsed)); // append
                repo.save(document);
            }
            else { // no prior history
                List<Usage> usages = new ArrayList<>();
                usages.add(new Usage(from, to, kwhUsed));
                repo.insert(new ServiceProxy(macAddress, usages));
            }
        }
    }
}
