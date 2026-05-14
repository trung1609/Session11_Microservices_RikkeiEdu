package com.example.subcriber.config;

import com.example.subcriber.service.ReceiveMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("pharmacy-alerts");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       MessageListenerAdapter adapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(adapter, topic());
        return container;
    }

    @Bean
    public MessageListenerAdapter adapter(ReceiveMessage receiveMessage){
        return new MessageListenerAdapter(receiveMessage, "onMessage");
    }
}
