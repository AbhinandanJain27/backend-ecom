package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Entity.Coupons;
import com.Abhinandan.Ecommerce.Enum.couponStatus;
import com.Abhinandan.Ecommerce.Repository.couponRepository;
import com.Abhinandan.Ecommerce.Service.couponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Coupons> updateCoupon(String name) {
        return couponRepository.findById(name).map(coupons -> {
            if(coupons.getStatus().equals(couponStatus.ACTIVE)){
                coupons.setStatus(couponStatus.EXPIRED);
            }else{
                coupons.setStatus(couponStatus.ACTIVE);
            }
            return couponRepository.save(coupons);
        });
    }

}
