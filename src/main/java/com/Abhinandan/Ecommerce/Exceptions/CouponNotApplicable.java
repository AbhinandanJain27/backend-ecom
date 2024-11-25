package com.Abhinandan.Ecommerce.Exceptions;

public class CouponNotApplicable extends RuntimeException{
    public CouponNotApplicable(float minAmount){
        super("Min Amount to use coupon :"+minAmount);
    }
}
