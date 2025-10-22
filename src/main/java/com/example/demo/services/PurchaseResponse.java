package com.example.demo.services;

import lombok.Data;

import java.util.UUID;

@Data
public class PurchaseResponse {
    private final String orderTrackingNumber;

    PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }


}

