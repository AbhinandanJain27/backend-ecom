package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Entity.Coupons;
import com.Abhinandan.Ecommerce.Enum.couponStatus;
import com.Abhinandan.Ecommerce.Enum.discountType;
import com.Abhinandan.Ecommerce.Service.couponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("coupon")
@CrossOrigin(origins = "https://localhost:4200")
public class couponController {

    @Autowired
    private couponServiceImpl couponService;

    @PostMapping("/addCoupon")
    public ResponseEntity<Coupons> addCoupon(@RequestBody Coupons coupon) {
        if(coupon.getCouponId() == null || coupon.getCouponId().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       return new ResponseEntity<Coupons>(couponService.addCoupon(coupon), HttpStatus.CREATED);
    }

    @GetMapping("/getAllCoupons")
    public List<Coupons> getAllUsers(){
        return couponService.getAllCoupons();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Coupons> getCouponByName(@PathVariable String name){
        Optional<Coupons> optionalCoupon = couponService.getCoupon(name);
        return optionalCoupon
                .map(coupon -> {
                    Coupons couponDto = new Coupons();
                    couponDto.setCouponId(coupon.getCouponId());
                    couponDto.setMinAmountToAvail(coupon.getMinAmountToAvail());
                    couponDto.setDiscountType(coupon.getDiscountType());
                    if(couponDto.getDiscountType()== discountType.PERCENTAGE){
                        couponDto.setDiscountPercent(coupon.getDiscountPercent());
                    }else{
                        couponDto.setDiscountValue(coupon.getDiscountValue());
                    }
                    Date today = new Date();
                    boolean expired = today.after(coupon.getExpirationDate()) || coupon.getExpirationTotalUsage()<1 ;
                    if(expired){
                        couponDto.setStatus(couponStatus.EXPIRED);
                    }else{
                        couponDto.setStatus(couponStatus.ACTIVE);
                        couponDto.setExpirationDate(coupon.getExpirationDate());
                        couponDto.setExpirationTotalUsage(coupon.getExpirationTotalUsage());
                    }
                    return ResponseEntity.ok(couponDto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a coupon
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name) {
        boolean deleted = couponService.deleteCoupon(name);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<Coupons> updateCouponStatus(@PathVariable String email, Coupons coupon) {
        Optional<Coupons> updatedCoupon = couponService.updateCoupon(email);

        return updatedCoupon.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
}
