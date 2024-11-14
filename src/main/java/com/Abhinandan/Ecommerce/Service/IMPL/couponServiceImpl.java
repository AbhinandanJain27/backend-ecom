package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Dto.applyCouponDto;
import com.Abhinandan.Ecommerce.Entity.Coupons;
import com.Abhinandan.Ecommerce.Enum.couponStatus;
import com.Abhinandan.Ecommerce.Enum.discountType;
import com.Abhinandan.Ecommerce.Enum.expirationType;
import com.Abhinandan.Ecommerce.Repository.couponRepository;
import com.Abhinandan.Ecommerce.Service.couponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class couponServiceImpl implements couponService {
    @Autowired
    private couponRepository couponRepository;

    @Override
    public Coupons addCoupon(Coupons coupon) {
        coupon.setStatus(couponStatus.ACTIVE);
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupons> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public List<Coupons> getAllActiveCoupons() {
        return couponRepository.findAllByStatus(couponStatus.ACTIVE);
    }

//    public boolean applyCoupon(String coupon){
//
//        if(isCouponExpired(coupon)){
//            updateCouponStatus(coupon);
//            return false;
//        }
//
//        // Add a function to calculate discount and update frontend to send accurate data to calculate discount and send
//        // the data back in json format to frontend Update the return types here also.
//        // and while applying coupon don't update current usages update it only when order is placed
//
//        return true;
//    }

    public ResponseEntity<?> applyCoupon(applyCouponDto applyCouponDto){

        if(isCouponExpired(applyCouponDto.getCoupon())){
            applyCouponDto.setMessage("Coupon Code Expired");
            applyCouponDto.setAmount(applyCouponDto.getAmount());
            applyCouponDto.setDiscount(applyCouponDto.getDiscount());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(applyCouponDto);
        }
        Optional <Coupons> retrievedCoupon = couponRepository.findById(applyCouponDto.getCoupon());
        if(retrievedCoupon.isPresent()){
            Coupons coupon = retrievedCoupon.get();
            if(applyCouponDto.getAmount() < coupon.getMinAmountToAvail()){
                applyCouponDto.setMessage("Minimum Amount To apply This Coupon is "+ coupon.getMinAmountToAvail());
                applyCouponDto.setDiscount(applyCouponDto.getDiscount());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(applyCouponDto);
            }else{
                double amount = applyCouponDto.getAmount();
                if(coupon.getDiscountType().equals(discountType.VALUE)){
                    applyCouponDto.setDiscount(coupon.getDiscountValue());
                }else{
                    float percentage = coupon.getDiscountPercent();
                    double discount = (amount * percentage)/100;
                    applyCouponDto.setDiscount((float)discount);
                }
                applyCouponDto.setMessage("Coupon Applied!");
            }
        }else{
            applyCouponDto.setMessage("No Such Coupon Exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(applyCouponDto);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(applyCouponDto);
    }

    @Override
    public Optional<Coupons> getCoupon(String name) {
        return couponRepository.findById(name);
    }

    @Override
    public boolean deleteCoupon(String name) {
        Optional<Coupons> coupon = couponRepository.findById(name);
        if (coupon.isPresent()) {
            couponRepository.delete(coupon.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Coupons> updateCouponStatus(String name) {
        return couponRepository.findById(name).map(coupons -> {
            if(coupons.getStatus().equals(couponStatus.ACTIVE)){
                coupons.setStatus(couponStatus.EXPIRED);
            }else{
                coupons.setStatus(couponStatus.ACTIVE);
            }
            return couponRepository.save(coupons);
        });
    }

    private boolean isCouponExpired(String coupon){
        Optional <Coupons> retrievedCoupon = couponRepository.findById(coupon);
        if(retrievedCoupon.isPresent()){
            if((retrievedCoupon.get().getStatus()).equals(couponStatus.EXPIRED)){
                return true;
            }else{
                if(retrievedCoupon.get().getExpirationType()== expirationType.ALLOWED_USAGE){
                    return !(retrievedCoupon.get().getMaximumAllowedUsages() > retrievedCoupon.get().getCurrentUsages());
                }else{
                    Date today = new Date();
                    return !(today.after(retrievedCoupon.get().getExpirationDate())) ;
                }
            }
        }
        return false;
    }

}
