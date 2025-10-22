package com.example.demo.services;
import lombok.Data;
import java.util.UUID;

@Data
public class PurchaseResponse {
    String orderTrackingNumber;
    PurchaseResponse(){
        this.orderTrackingNumber= UUID.randomUUID().toString();
    }
}

