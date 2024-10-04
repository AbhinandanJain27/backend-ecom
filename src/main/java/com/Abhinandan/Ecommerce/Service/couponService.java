package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Entity.Coupons;

import java.util.List;
import java.util.Optional;

public interface couponService {
    Coupons addCoupon(Coupons coupon);
    List<Coupons> getAllCoupons();
    Optional<Coupons> getCoupon(String name);
    boolean deleteCoupon(String name);
}
