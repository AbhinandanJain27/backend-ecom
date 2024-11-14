package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Dto.orderDto;
import com.Abhinandan.Ecommerce.Enum.orderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = {"user", "cartItems","coupon"})
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

//    @Enumerated(EnumType.STRING)
    private orderStatus orderStatus;

    private UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_email", referencedColumnName = "email")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="couponCode", referencedColumnName = "couponId")
    @JsonIgnore
    private Coupons coupon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @JsonManagedReference
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CartItem> cartItems;

    public orderDto getOrderDto(){
        orderDto orderDto = new orderDto();

        orderDto.setOrderId(orderId);
        orderDto.setDate(date);
        orderDto.setAmountPaid(amountPaid);
        orderDto.setDiscount(discount);
        orderDto.setAddress(address);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setTrackingId(trackingId);
        if(coupon != null){
            orderDto.setCoupon(coupon.getCouponId());
        }
        return orderDto;
    }
}
