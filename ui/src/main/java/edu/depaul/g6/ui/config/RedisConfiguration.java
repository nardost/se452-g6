package edu.depaul.g6.ui.config;

import edu.depaul.g6.delivery.service.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.util.UUID;
import java.util.concurrent.Executors;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    Receiver receiver;

    @Autowired
    public void setReceiver(Receiver receiver) { this.receiver = receiver; }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(redisHost);
        standaloneConfiguration.setPort(redisPort);
        standaloneConfiguration.setPassword(redisPassword);
        return new LettuceConnectionFactory(standaloneConfiguration);
    }

    @Bean(name = "messaging")
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(String.class));
        return redisTemplate;
    }

    @Bean(name = "receive")
    ChannelTopic receiveChannelTopic() {
        return new ChannelTopic("meter-reading");
    }

    @Bean(name = "send")
    ChannelTopic sendChannelTopic() {
        return new ChannelTopic("signal");
    }

    /*
     * There was a duplicate redisMessageListenerContainer somewhere
     * and I had to rename this bean to listenerContainer. Where is
     * that other duplicate bean???
     */
    @Bean
    RedisMessageListenerContainer listenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory());
        container.addMessageListener(new MessageListenerAdapter(receiver), receiveChannelTopic());
        container.setTaskExecutor(Executors.newFixedThreadPool(10));
        return container;
    }
}
