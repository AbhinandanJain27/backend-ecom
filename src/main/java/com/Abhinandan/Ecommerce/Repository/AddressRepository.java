package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository <Address,Long> {
    List <Address> findAllByUserEmail(String email);
}
