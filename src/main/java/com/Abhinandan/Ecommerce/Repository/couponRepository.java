package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Abhinandan.Ecommerce.Enum.couponStatus;

import java.util.List;

@Repository
public interface couponRepository extends JpaRepository<Coupons,String> {
    List<Coupons> findAllByStatus(couponStatus status);
}
