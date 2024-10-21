package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
