package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Dto.applyCouponDto;
import com.Abhinandan.Ecommerce.Entity.Coupons;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface couponService {
    Coupons addCoupon(Coupons coupon);
    List<Coupons> getAllCoupons();
    List<Coupons> getAllActiveCoupons();
    Optional<Coupons> getCoupon(String name);
    boolean deleteCoupon(String name);
    Optional<Coupons> updateCouponStatus(String name);
    ResponseEntity<?> applyCoupon(applyCouponDto coupon);
    boolean isCouponExpired(String coupon);
}
