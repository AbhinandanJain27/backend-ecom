package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface orderRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByTrackingId(UUID trackingID);
}
