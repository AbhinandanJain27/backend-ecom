package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import com.Abhinandan.Ecommerce.Dto.orderDto;
import com.Abhinandan.Ecommerce.Service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private cartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody addProductInCartDto product){
        return cartService.addProductToCart(product);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getCartByEmail(@PathVariable String email){
        orderDto orderDto = cartService.getCartById(email);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }
}
