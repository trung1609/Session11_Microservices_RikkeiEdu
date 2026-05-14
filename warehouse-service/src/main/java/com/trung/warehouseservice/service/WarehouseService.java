package com.trung.warehouseservice.service;

import com.trung.warehouseservice.event.OrderEvent;
import com.trung.warehouseservice.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    @KafkaListener(
            topics = "medicine-stock-events",
            groupId = "warehouse-stock"
    )
    @Transactional
    public void processOrder(OrderEvent orderEvent) {

        log.info("===== Xử lý sự kiện đơn hàng =====");
        log.info("Order ID: {}", orderEvent.getOrderId());
        log.info("Medicine ID: {}", orderEvent.getMedicineId());
        log.info("Quantity: {}", orderEvent.getQuantity());

        try {
            int rowsUpdated = warehouseRepository.decreaseStock(
                    orderEvent.getMedicineId(),
                    orderEvent.getQuantity()
            );

            if (rowsUpdated > 0) {
                log.info("Hoàn tất trừ kho cho đơn hàng: {}",
                        orderEvent.getOrderId());
            } else {
                log.warn("Thất bại: Kho không đủ số lượng hoặc sai Medicine ID: {}",
                        orderEvent.getMedicineId());
            }
        } catch (Exception e) {
            log.error("Lỗi Database khi trừ kho - Order ID: {} - Error: {}",
                    orderEvent.getOrderId(), e.getMessage(), e);
        }

    }

}
