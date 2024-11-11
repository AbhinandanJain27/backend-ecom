package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Entity.Coupons;
import com.Abhinandan.Ecommerce.Enum.couponStatus;

import java.util.List;
import java.util.Optional;

public interface couponService {
    Coupons addCoupon(Coupons coupon);
    List<Coupons> getAllCoupons();
    List<Coupons> getAllActiveCoupons();
    Optional<Coupons> getCoupon(String name);
    boolean deleteCoupon(String name);
    Optional<Coupons> updateCoupon(String name);
}
