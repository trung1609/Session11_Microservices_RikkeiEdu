package com.example.subcriber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyAlert {
    private String type;
    private String message;
}
