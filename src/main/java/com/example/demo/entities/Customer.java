package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "customer_first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "customer_last_name")
    private String lastName;

    @NotNull
    @NotBlank
    @Column(name = "address")
    private String address;

    @NotNull
    @NotBlank
    @Column(name = "postal_code")
    private String postal_code;

    @NotNull
    @NotBlank
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @NotNull
    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Cart> carts = new HashSet<>();

    public void add(Cart cart) {
        if (cart != null) {
            if (this.carts == null) {
                this.carts = new HashSet<>();
            }
            this.carts.add(cart);
            cart.setCustomer(this);
        }
    }


}
