package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import com.Abhinandan.Ecommerce.Entity.CartItem;
import com.Abhinandan.Ecommerce.Entity.Orders;
import com.Abhinandan.Ecommerce.Entity.Product;
import com.Abhinandan.Ecommerce.Entity.User;
import com.Abhinandan.Ecommerce.Enum.orderStatus;
import com.Abhinandan.Ecommerce.Repository.*;
import com.Abhinandan.Ecommerce.Service.cartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ResponseEntity<?> addProductToCart(addProductInCartDto addProductInCart) {
        // Find the active order for the user
        Orders activeOrder = orderRepository.findByUserEmailAndOrderStatus(addProductInCart.getEmail(), orderStatus.PENDING);
        if (activeOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Active order not found for user");
        }

        // Check if the CartItem already exists
        Optional<CartItem> optionalCartItems = cartItemsRepository
                .findByProductProductIdAndOrderOrderIdAndUserEmail(addProductInCart.getProductId(), activeOrder.getOrderId(), addProductInCart.getEmail());

        if (optionalCartItems.isPresent()) {
            // If present, update quantity and total amount
            CartItem existingCartItem = optionalCartItems.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + existingCartItem.getPrice());
            cartItemsRepository.save(existingCartItem);
            orderRepository.save(activeOrder);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product already in cart, cart values updated!");
        } else {
            // Fetch the Product and User entities
            Optional<Product> optionalProduct = productRepository.findById(addProductInCart.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCart.getEmail());

            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                Product product = optionalProduct.get();
                User user = optionalUser.get();

                CartItem cart = new CartItem();
                cart.setProduct(product); // Ensure this is set
                cart.setPrice(product.getPrice());
                cart.setQuantity(1L);
                cart.setUser(user);
                cart.setOrder(activeOrder);

                // Save the cart item
                CartItem updatedCart = cartItemsRepository.save(cart);

                // Update order total and cart items
                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + updatedCart.getPrice());
                activeOrder.getCartItems().add(updatedCart);
                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
            }
        }
    }
}
