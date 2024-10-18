package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Repository.*;
import com.Abhinandan.Ecommerce.Service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cartServiceImpl implements cartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private cartItemsRepository cartItemsRepository;
}
