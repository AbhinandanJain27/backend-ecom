package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.Orders;
import com.Abhinandan.Ecommerce.Enum.orderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface orderRepository extends JpaRepository<Orders, Long> {

    Orders findByTrackingId(UUID trackingID);

    Orders findByOrderIdAndOrderStatus(Long orderId, orderStatus orderStatus);

    Orders findByUserEmailAndOrderStatus(String email, orderStatus orderStatus);
}
