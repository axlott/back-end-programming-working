package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        PurchaseResponse response=new PurchaseResponse();
        Purchase order = new Purchase(response.orderTrackingNumber);
        return response;
    }
}
