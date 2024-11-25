package com.Abhinandan.Ecommerce.Exceptions;

public class CouponNotFoundException extends RuntimeException{

    public CouponNotFoundException(){
        super("Coupon Not Found");
    }

}
