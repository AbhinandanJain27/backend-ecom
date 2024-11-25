package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Dto.addProductInCartDto;
import com.Abhinandan.Ecommerce.Dto.cartItemsDto;
import com.Abhinandan.Ecommerce.Dto.orderDto;
import com.Abhinandan.Ecommerce.Entity.*;
import com.Abhinandan.Ecommerce.Enum.couponStatus;
import com.Abhinandan.Ecommerce.Enum.discountType;
import com.Abhinandan.Ecommerce.Enum.expirationType;
import com.Abhinandan.Ecommerce.Enum.orderStatus;
import com.Abhinandan.Ecommerce.Exceptions.CouponExpiredException;
import com.Abhinandan.Ecommerce.Exceptions.CouponNotApplicable;
import com.Abhinandan.Ecommerce.Exceptions.CouponNotFoundException;
import com.Abhinandan.Ecommerce.Repository.*;
import com.Abhinandan.Ecommerce.Service.cartService;
import com.Abhinandan.Ecommerce.Service.couponService;
import com.Abhinandan.Ecommerce.Utils.EmailContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private couponRepository couponRepository;

    @Autowired
    private couponService couponService;

    @Transactional
    public ResponseEntity<?> addProductToCart(addProductInCartDto addProductInCart) {
        // Find the active order for the user
        Map<String, String> response = new HashMap<>();
        Orders activeOrder = orderRepository.findByUserEmailAndOrderStatus(addProductInCart.getEmail(), orderStatus.PENDING);
        if (activeOrder == null) {
            response.put("message","No Active Order's Found For User");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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
            response.put("message","Products Already In Cart");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            // Fetch the Product and User entities
            Optional<Product> optionalProduct = productRepository.findById(addProductInCart.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCart.getEmail());
            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                Product product = optionalProduct.get();
                User user = optionalUser.get();

                CartItem cart = new CartItem();
                 // Ensure this is set
                cart.setPrice(product.getPrice());
                cart.setQuantity(1L);
                cart.setUser(user);
                cart.setOrder(activeOrder);
                cart.setProduct(product);
                // Save the cart item
                CartItem updatedCart = cartItemsRepository.save(cart);

                // Update order total and cart items
                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + updatedCart.getPrice());
                activeOrder.getCartItems().add(updatedCart);
                orderRepository.save(activeOrder);
                response.put("message","Product Added to cart");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else if(optionalUser.isPresent()){
                response.put("message","Product Not Found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else{
                response.put("message","User Not Found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }
    }

    @Override
    public ResponseEntity<?> removeProductFromCart(addProductInCartDto addProductInCart) {
        return null;
    }

    @Override
    public orderDto applyCoupon(String couponCode){
        Orders activeOrder = orderRepository.findByUserEmailAndOrderStatus(EmailContext.getEmail(),orderStatus.PENDING);
        Coupons coupon = couponRepository.findById(couponCode).orElseThrow(CouponNotFoundException::new);

        if(couponService.isCouponExpired(couponCode)){
            if(coupon.getStatus().equals(couponStatus.ACTIVE)){
                coupon.setStatus(couponStatus.EXPIRED);
                couponRepository.save(coupon);
            }
            throw new CouponExpiredException();
        }

        double discountAmount = calcDiscount(coupon, activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount ;

        activeOrder.setDiscount((long)discountAmount);
        activeOrder.setAmountPaid((long)netAmount);

        if(activeOrder.getCoupon()==null){
            activeOrder.setCoupon(coupon);
            coupon.setCurrentUsages(coupon.getCurrentUsages()+1);
            couponRepository.save(coupon);
        }

        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    public orderDto removeCoupon(){
        Orders activeOrder = orderRepository.findByUserEmailAndOrderStatus(EmailContext.getEmail(),orderStatus.PENDING);
        if(activeOrder.getCoupon() != null){
            Coupons coupon = couponRepository.findById(activeOrder.getCoupon().getCouponId()).orElseThrow(CouponNotFoundException::new);
            activeOrder.setCoupon(null);
            activeOrder.setDiscount(0);
            activeOrder.setAmountPaid(activeOrder.getTotalAmount());
            orderRepository.save(activeOrder);
            coupon.setCurrentUsages(coupon.getCurrentUsages() -1 );
            couponRepository.save(coupon);
        }else{
            throw new CouponExpiredException();
        }
        return activeOrder.getOrderDto();
    }
    @Override
    public orderDto getCartById(String email){
        Orders activeOrder = orderRepository.findByUserEmailAndOrderStatus(EmailContext.getEmail(), orderStatus.PENDING);

        List <cartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItem::getCartDto).toList();
        orderDto orderDto = new orderDto();
        orderDto.setAmountPaid(activeOrder.getAmountPaid());
        orderDto.setOrderId(activeOrder.getOrderId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);

        if(activeOrder.getCoupon() != null){
            orderDto.setCoupon(activeOrder.getCoupon().getCouponId());
        }
        return orderDto;
    }

    private double calcDiscount(Coupons coupon, double amount){
        if(amount>=coupon.getMinAmountToAvail()){
            if(coupon.getDiscountType().equals(discountType.VALUE)){
                return coupon.getDiscountValue();
            }else{
                return ( amount * coupon.getDiscountPercent()) / 100;
            }
        }else{
            throw new CouponNotApplicable(coupon.getMinAmountToAvail());
        }
    }
}
