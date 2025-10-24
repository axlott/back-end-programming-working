package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class is just for loading some sample data into the database when the app starts.
 * It's a @Component so Spring finds it, and because it implements CommandLineRunner,
 * the run() method gets executed automatically.
 */
@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    /**
     * Constructor that gets the repositories we need. Spring's dependency injection
     * handles providing the actual repository objects for us.
     * @param customerRepository The repository for accessing customer data.
     * @param divisionRepository The repository for getting division info.
     */
    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    /**
     * This is the method that actually runs. It checks if there are any customers
     * already, and if not, it adds five sample ones so the database isn't empty.
     * @param args Command line arguments.
     * @throws Exception if something goes wrong.
     */
    @Override
    public void run(String... args) throws Exception {
        // When the Customer table is empty.
        if (customerRepository.count() == 0) {

            // Fetch Arizona (ID=2) from the database into a new Division object.
            Division division = divisionRepository.findById(2L).orElse(null);

            if (division != null) {
                // Create and save 5 sample customers
                Customer c1 = new Customer();
                c1.setFirstName("John");
                c1.setLastName("Doe");
                c1.setAddress("123 Main St");
                c1.setPostal_code("85001");
                c1.setPhone("555-1234");
                c1.setDivision(division);
                customerRepository.save(c1);

                Customer c2 = new Customer();
                c2.setFirstName("Jane");
                c2.setLastName("Smith");
                c2.setAddress("456 Main Ave");
                c2.setPostal_code("85002");
                c2.setPhone("555-5678");
                c2.setDivision(division);
                customerRepository.save(c2);

                Customer c3 = new Customer();
                c3.setFirstName("Alex");
                c3.setLastName("Williams");
                c3.setAddress("789 Oak Ave");
                c3.setPostal_code("85003");
                c3.setPhone("555-9101");
                c3.setDivision(division);
                customerRepository.save(c3);

                Customer c4 = new Customer();
                c4.setFirstName("Alexander");
                c4.setLastName("Poppins");
                c4.setAddress("101 Broadway St");
                c4.setPostal_code("85004");
                c4.setPhone("555-1121");
                c4.setDivision(division);
                customerRepository.save(c4);

                Customer c5 = new Customer();
                c5.setFirstName("Mary");
                c5.setLastName("Brown");
                c5.setAddress("321 W 13th ST");
                c5.setPostal_code("85005");
                c5.setPhone("555-3141");
                c5.setDivision(division);
                customerRepository.save(c5);

                System.out.println("Sample customers created by BootStrapData.");
            } else {
                System.out.println("Default division not found. Could not create sample customers.");
            }
        }
    }
}
