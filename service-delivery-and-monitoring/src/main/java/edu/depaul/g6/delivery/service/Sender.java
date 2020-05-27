package edu.depaul.g6.delivery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Sender {

    private final RedisTemplate<String, String> redisTemplate;
    private final ChannelTopic channelTopic;

    @Autowired
    public Sender(
            @Qualifier("messaging") RedisTemplate<String, String> template,
            @Qualifier("send") ChannelTopic topic) {
        this.redisTemplate = template;
        this.channelTopic = topic;
    }

    public void send(String message) {
        final String channel = channelTopic.getTopic();
        redisTemplate.convertAndSend(channel, message);
        log.info(String.format("SENT [%s] %s", channel, message));
    }
}
