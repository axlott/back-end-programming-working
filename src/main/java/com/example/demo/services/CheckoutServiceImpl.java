package com.example.demo.services;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.ExcursionRepository;
import com.example.demo.dao.VacationRepository;
import com.example.demo.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This is where the real business logic for the checkout happens.
 * It implements the CheckoutService interface and does all the heavy lifting.
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CustomerRepository customerRepository;
    private final VacationRepository vacationRepository;
    private final ExcursionRepository excursionRepository;

    /**
     * This is the main method that processes an order.
     * The strategy is to not trust the incoming objects, use their IDs to fetch the
     * "real" managed entities from the DB, and then build a new Cart to save.
     * This avoids all those detached entity exceptions.
     * @param purchase The DTO from the front-end with all the order data.
     * @return A response object with the tracking number.
     */
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        // Creates and populate a new Cart with the DTO object
        Cart cart = new Cart();
        cart.setOrderTrackingNumber(UUID.randomUUID().toString());
        cart.setStatus(StatusType.ordered);
        cart.setPackage_price(purchase.getCart().getPackage_price());
        cart.setParty_size(purchase.getCart().getParty_size());

        // Creates and populate a new CartItem for each of the CartItems from the front-end (DTO)
        for (CartItem itemDto : purchase.getCartItems()) {
            CartItem newCartItem = new CartItem();

            // Fetch the Vacation object from the DB using its ID from the DTO.
            Vacation vacation = vacationRepository.findById(itemDto.getVacation().getId()).orElse(null);
            newCartItem.setVacation(vacation);

            // New Set of Excursions to be populated with the excursions in each Item in the Cart DTO.
            Set<Excursion> excursions = new HashSet<>();
            if (itemDto.getExcursions() != null) {
                for (Excursion excursionDto : itemDto.getExcursions()) {
                    // Fetch the Excursion object from the DB using its ID from the DTO.
                    Excursion excursion = excursionRepository.findById(excursionDto.getId()).orElse(null);
                    if (excursion != null) {
                        // Adds this fetched excursion to the set
                        excursions.add(excursion);
                    }
                }
            }
            // Assigns the new excursion set as the excursion Set for this new CartItem object
            newCartItem.setExcursions(excursions);

            // Adds the new CartItem object to the new Cart object, using its dedicated function and adding them in both directions.
            cart.add(newCartItem);
        }


        // Finally, it fetches the Customer object from the DB
        Customer customer = customerRepository.findById(purchase.getCustomer().getId()).orElse(null);
        if (customer != null) {
            //Adds it to the Cart (bidirectional)
            customer.add(cart);
        }
        // Saves. Since all the Entities changed are ruled by Cascade.ALL, it would be enough to save `customer` to produce a saving chain reaction
        customerRepository.save(customer);

        // Responds with the Order Tracking Number
        return new PurchaseResponse(cart.getOrderTrackingNumber());
    }

    /**
     * The constructor gets all the different repositories injected by Spring.
     * We need these to fetch the real, managed entities from the database.
     * @param customerRepository Repo for customer stuff.
     * @param vacationRepository Repo for vacation stuff.
     * @param excursionRepository Repo for excursion stuff.
     */
    public CheckoutServiceImpl(CustomerRepository customerRepository, VacationRepository vacationRepository, ExcursionRepository excursionRepository) {
        this.customerRepository = customerRepository;
        this.vacationRepository = vacationRepository;
        this.excursionRepository = excursionRepository;
    }
}