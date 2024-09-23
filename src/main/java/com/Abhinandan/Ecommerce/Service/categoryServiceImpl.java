package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Entity.category;
import com.Abhinandan.Ecommerce.Repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class categoryServiceImpl implements categoryService{
    @Autowired
    private categoryRepository categoryRepository;
    @Override
    public category saveCategory(category category){
        return categoryRepository.save(category);
    }
    @Override
    public List<category> getAllCategories(){
        return categoryRepository.findAll();
    }
    @Override
    public Optional<category> findById(Long id){
        return categoryRepository.findById(id);
    }
    @Override
    public boolean deleteUser(Long id){
        Optional <category> category = findById(id);
        if(category.isPresent()){
            categoryRepository.delete(category.get());
            return true;
        }
    return false;
    }

}
