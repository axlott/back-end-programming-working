package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @NotNull
    @Column(name = "package_price")
    private BigDecimal package_price;

    @NotNull
    @Column(name = "party_size")
    private int party_size;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @NotNull
    @NotBlank
    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

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
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItems = new HashSet<>();

    public void add(CartItem item) {
        if (item != null) {
            if (this.cartItems == null) {
                this.cartItems = new HashSet<>();
            }
            this.cartItems.add(item);
            item.setCart(this);
        }
    }


}
