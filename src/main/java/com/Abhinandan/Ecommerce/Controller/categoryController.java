package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Entity.category;
import com.Abhinandan.Ecommerce.Service.categoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "https://localhost:4200")
public class categoryController {
    @Autowired
    private categoryServiceImpl categoryService;
    @PostMapping("/category")
    public ResponseEntity<category> createCategory(@RequestBody category category){
        return new ResponseEntity<category>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }
    @GetMapping("/Categories")
    public List<category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public ResponseEntity<category> getById(@PathVariable long id){
        Optional<category> optionalCategory = categoryService.findById(id); // Assuming findByEmail exists
        return optionalCategory
                .map(category -> {
                    category categoryDto = new category();
                    categoryDto.setName(category.getName());
                    return ResponseEntity.ok(categoryDto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        boolean deleted= categoryService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }
}
