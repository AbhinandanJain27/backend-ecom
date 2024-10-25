package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Dto.productDto;
import com.Abhinandan.Ecommerce.Service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class productController {

    @Autowired
    private productService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addProduct")
    public ResponseEntity<productDto> addProduct(@ModelAttribute productDto productDto) throws IOException{
        productDto productDto1 = this.productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    public ResponseEntity <List<productDto>> getAllProducts(){
        List<productDto> productDtos = this.productService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity <List<productDto>> getAllProductsByName(@PathVariable String name){
        List<productDto> productDtos = this.productService.getAllProductsByName(name);
        return ResponseEntity.ok(productDtos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }
}
