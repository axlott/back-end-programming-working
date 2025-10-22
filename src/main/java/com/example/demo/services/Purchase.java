package com.example.demo.services;

import lombok.Data;

@Data
public class Purchase {
    Long customer;
    Long cart;
    String TrackingNumber;
    Purchase(String orderTrackingNumber){
        this.customer=1L;
        this.cart=1L;
        this.TrackingNumber=orderTrackingNumber;
    }

}
