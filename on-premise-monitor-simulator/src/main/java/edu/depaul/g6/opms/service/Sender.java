package edu.depaul.g6.opms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class Sender {

    private final RedisTemplate<String, String> redisTemplate;
    private final ChannelTopic  channelTopic;

    @Autowired
    public Sender(@Qualifier("messaging") RedisTemplate<String, String> template,
                  @Qualifier("send") ChannelTopic topic) {
        this.redisTemplate = template;
        this.channelTopic = topic;
    }

    public void send(String message) {
        final String channel = channelTopic.getTopic();
        redisTemplate.convertAndSend(channel, message);
        log.info(String.format("SENT [%s] %s", channel, message));
    }

 /*   String mockup = "";
        for (String meter : meters) {
        mockup += String.format("%s,%s,%s,%d;", meter, Instant.now().minus(1, ChronoUnit.SECONDS),
                Instant.now(), r.nextInt(1000));
    }

    // log.info(String.format("RECEIVED FROM [%s] %s", new String(message.getChannel()), mockup));

    byte[] array = mockup.getBytes();
    String[] meters = new String(array).split(";");

        for (String meter : meters) {
        String[] components = meter.split(",");

        // better semantics:
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
    }*/
}
