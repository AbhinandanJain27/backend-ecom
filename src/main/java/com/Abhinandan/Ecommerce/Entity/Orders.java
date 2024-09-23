//package com.Abhinandan.Ecommerce.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import java.util.Date;
//
//@Entity
//@Data
//@Table(name="orders")
//public class Orders {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long orderId;
//    private String email;
//    private Date date;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name="product_id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Products product;
//}
