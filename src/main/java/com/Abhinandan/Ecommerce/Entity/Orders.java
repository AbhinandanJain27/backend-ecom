package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Enum.orderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = {"user", "cartItems"})
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private Date date;

    private double amountPaid;

    private double totalAmount;

    private double discount;

    private String address;

    private orderStatus orderStatus;

    private UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_email", referencedColumnName = "email")
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @JsonManagedReference
    private List<CartItem> cartItems;

}
