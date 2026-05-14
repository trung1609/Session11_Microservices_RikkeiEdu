package com.example.subcriber.service;

import com.example.subcriber.entity.PharmacyAlert;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiveMessage implements MessageListener {
    private final ObjectMapper objectMapper;
    @Override
    public void onMessage(Message message, byte @Nullable [] pattern) {
        try {
            PharmacyAlert alert = objectMapper.readValue(message.getBody(), PharmacyAlert.class);

            log.info("--- THÔNG BÁO MỚI TẠI DASHBOARD ---");
            log.info("Loại: {}", alert.getType());
            log.info("Nội dung: {}", alert.getMessage());
            log.info("----------------------------------");
        } catch (IOException e) {
            log.error("Lỗi khi deserialize message từ Redis", e);
        }
    }

}
