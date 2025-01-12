package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Enum.couponStatus;
import com.Abhinandan.Ecommerce.Enum.discountType;
import com.Abhinandan.Ecommerce.Enum.expirationType;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
@Entity
@Table(name="Coupons")
public class    Coupons {
    @Id
    private String CouponId;

    @Enumerated(EnumType.STRING)
    private couponStatus status;

    @Enumerated(EnumType.STRING)
    private discountType discountType;

    @Enumerated(EnumType.STRING)
    private expirationType expirationType;

    private float minAmountToAvail;

    private float discountPercent;

    private float discountValue;

    private Date expirationDate;

    private int maximumAllowedUsages;

    private int currentUsages;
}
