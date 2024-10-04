//package com.Abhinandan.Ecommerce.Entity;
//
//import com.Abhinandan.Ecommerce.Enum.orderStatus;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Data
//@Table(name="orders")
//public class Orders {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long orderId;
//
//    private Date date;
//
//    private double amount;
//
//    private double totalAmount;
//
//    private orderStatus orderStatus;
//
//    private UUID trackingId;
//
//    private String email;
//
//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name="user_id", referencedColumnName = "id")
//    private User user;
//
//    private List<CartItem> cartItems;
//
////    @ManyToOne(fetch = FetchType.LAZY, optional = false)
////    @JoinColumn(name="product_id",nullable = false)
////    @OnDelete(action = OnDeleteAction.CASCADE)
////    @JsonIgnore
////    private Products product;
//}
