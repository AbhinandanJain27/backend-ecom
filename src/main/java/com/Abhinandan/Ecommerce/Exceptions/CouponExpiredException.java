package com.Abhinandan.Ecommerce.Exceptions;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException(){
        super("Coupon Code Expired!");
    }
}
