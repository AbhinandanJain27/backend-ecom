package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface cartItemsRepository extends JpaRepository<CartItem, Long> {

//    Optional<CartItem> findProductIdAndOrderIdAndUserid(Long productId, Long OrderId, Long orderId);
}
