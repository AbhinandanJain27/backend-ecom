package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import com.Abhinandan.Ecommerce.Entity.CartItem;
import com.Abhinandan.Ecommerce.Entity.Orders;
import com.Abhinandan.Ecommerce.Enum.orderStatus;
import com.Abhinandan.Ecommerce.Repository.*;
import com.Abhinandan.Ecommerce.Service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class cartServiceImpl implements cartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private cartItemsRepository cartItemsRepository;

    public ResponseEntity<?> addProductToCart(addProductInCartDto addProductInCart){
        Orders activeOrder = orderRepository.findByUserIdAndStatus(addProductInCart.getOrderId(), orderStatus.PENDING);
        Optional < CartItem > cartItems = cartItemsRepository.findProductIdAndOrderIdAndUserid(addProductInCart.getProductId(), activeOrder.getOrderId(), addProductInCart.getOrderId());

    }
}
