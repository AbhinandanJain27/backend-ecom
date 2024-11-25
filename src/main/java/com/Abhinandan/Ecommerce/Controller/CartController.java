package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import com.Abhinandan.Ecommerce.Dto.orderDto;
import com.Abhinandan.Ecommerce.Exceptions.CouponExpiredException;
import com.Abhinandan.Ecommerce.Exceptions.CouponNotApplicable;
import com.Abhinandan.Ecommerce.Exceptions.CouponNotFoundException;
import com.Abhinandan.Ecommerce.Service.cartService;
import com.Abhinandan.Ecommerce.Service.couponService;
import com.Abhinandan.Ecommerce.Utils.EmailContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private cartService cartService;

    @Autowired
    private couponService couponService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody addProductInCartDto product){
        return cartService.addProductToCart(product);
    }

    @GetMapping()
    public ResponseEntity<?> getCartByEmail(){
        orderDto orderDto = cartService.getCartById(EmailContext.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @DeleteMapping("/removeFromCart")
    public ResponseEntity<?> removeProductFromCArt(@RequestBody addProductInCartDto product){
        return cartService.removeProductFromCart(product);
    }

    @PostMapping("/applyCoupon/{couponCode}")
    public ResponseEntity<?> applyCoupon(@PathVariable String couponCode){
       try{
           orderDto orderDto = cartService.applyCoupon(couponCode);
           return ResponseEntity.ok(orderDto);
       }catch(CouponNotApplicable | CouponNotFoundException | CouponExpiredException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
       }
    }

    @GetMapping("/removeCoupon")
    public ResponseEntity<?> removeCoupon(){
        try{
            orderDto orderDto = cartService.removeCoupon();
            return ResponseEntity.ok(orderDto   );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
