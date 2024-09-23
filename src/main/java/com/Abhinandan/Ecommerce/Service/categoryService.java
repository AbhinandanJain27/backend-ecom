package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Entity.category;

import java.util.List;
import java.util.Optional;

public interface categoryService {
    category saveCategory(category category);
    List<category> getAllCategories();
    Optional<category> findById(Long id);
    boolean deleteUser(Long id);
}
