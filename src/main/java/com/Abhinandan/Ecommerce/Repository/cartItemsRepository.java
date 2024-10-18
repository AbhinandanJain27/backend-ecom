package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cartItemsRepository extends JpaRepository<CartItem, Long> {
}
