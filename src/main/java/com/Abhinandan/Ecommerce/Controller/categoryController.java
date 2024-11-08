package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Entity.category;
import com.Abhinandan.Ecommerce.Service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "https://localhost:4200")
public class categoryController {
    @Autowired
    private categoryService categoryService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addCategory")
    public ResponseEntity<category> createCategory(@RequestBody category category){
        return new ResponseEntity<category>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }
    @GetMapping("/Categories")
    public List<category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public ResponseEntity<category> getById(@PathVariable long id){
        Optional<category> optionalCategory = categoryService.findById(id);
        return optionalCategory
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id){
        boolean deleted= categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }
}
