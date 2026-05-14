package com.trung.pharmacyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellRequest {
    private String medicineId;
    private Integer quantity;
    private String userEmail;
}
