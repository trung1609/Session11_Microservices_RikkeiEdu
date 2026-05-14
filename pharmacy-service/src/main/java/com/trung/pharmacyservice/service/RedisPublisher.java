package com.trung.pharmacyservice.service;

import com.trung.pharmacyservice.entity.Medicine;
import com.trung.pharmacyservice.entity.PharmacyAlert;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class RedisPublisher {
    private final RedisTemplate<String, Medicine> redisTemplate;
    private final ChannelTopic topic;
    private final ObjectMapper objectMapper;

    public void publishAlert(String type, String message) {
        try {
            PharmacyAlert alert = new PharmacyAlert(type, message);
            redisTemplate.convertAndSend(topic.getTopic(), alert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
