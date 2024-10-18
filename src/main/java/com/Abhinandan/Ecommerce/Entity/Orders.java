package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Enum.orderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private Date date;

    private double amount;

    private double totalAmount;

    private String address;

    private orderStatus orderStatus;

    private UUID trackingId;

    private String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_email", referencedColumnName = "email")
    private User user;

    @OneToMany
    private List<CartItem> cartItems;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name="product_id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Product product;

    // I haven't set the amount, total amount, discount, order status as null while creating cart for the user
}
