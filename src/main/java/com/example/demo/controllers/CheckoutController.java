package com.example.demo.controllers;

import com.example.demo.services.CheckoutService;
import com.example.demo.services.CheckoutServiceImpl;
import com.example.demo.services.Purchase;
import com.example.demo.services.PurchaseResponse;
import org.springframework.web.bind.annotation.*;

/**
 * This is the REST controller that handles the actual checkout process.
 * It basically creates a custom URL endpoint at /api/checkout.
 */
@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("http://localhost:4200")
public class CheckoutController {
    private final CheckoutService checkoutService;

    /**
     * Constructor for the controller. It gets the CheckoutService injected
     * so it can pass off the real work to the service layer.
     * @param checkoutService The service that does all the checkout logic.
     */
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /**
     * This method handles the POST request when a user submits their purchase.
     * It takes the JSON data from the front-end, turns it into a Purchase object,
     * and sends it to the service to be processed.
     * @param purchase A DTO with all the order info.
     * @return A response with the order tracking number.
     */
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        return this.checkoutService.placeOrder(purchase);
    }
}
