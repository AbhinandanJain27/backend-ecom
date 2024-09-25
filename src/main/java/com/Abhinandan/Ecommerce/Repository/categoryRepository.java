package com.Abhinandan.Ecommerce.Repository;

import com.Abhinandan.Ecommerce.Entity.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository <category,Long> {

}
