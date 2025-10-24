package com.example.demo.services;

/**
 * The Interface used by the controller, that connect both Controller with its Implementation
 */
public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
