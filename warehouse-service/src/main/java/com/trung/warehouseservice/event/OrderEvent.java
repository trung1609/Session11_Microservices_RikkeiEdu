package com.trung.warehouseservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String orderId;
    private String medicineId;
    private String userEmail;
    private Integer quantity;
    private LocalDateTime timestamp;
}
