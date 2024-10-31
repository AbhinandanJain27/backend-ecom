package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import com.Abhinandan.Ecommerce.Service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private cartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody addProductInCartDto product){
        return cartService.addProductToCart(product);
    }
}
