package com.example.demo.services;

import lombok.Data;

import java.awt.*;
import java.util.UUID;

@Data
public class PurchaseResponse {
    /**
     * This class manages the data
     */
    private final String orderTrackingNumber;

    // Constructor for the response. It's used to communicate the Order Tracking Number
    PurchaseResponse(String orderTrackingNumber){
        this.orderTrackingNumber=orderTrackingNumber;
    }
}
