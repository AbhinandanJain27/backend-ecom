package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import com.Abhinandan.Ecommerce.Dto.orderDto;
import org.springframework.http.ResponseEntity;

public interface cartService {
    ResponseEntity<?> addProductToCart(addProductInCartDto addProductInCart);
    ResponseEntity<?> removeProductFromCart(addProductInCartDto addProductInCart);
    orderDto getCartById(String email);
}
