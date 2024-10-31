package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import org.springframework.http.ResponseEntity;

public interface cartService {
    ResponseEntity<?> addProductToCart(addProductInCartDto addProductInCart);
}
