package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Dto.productDto;
import com.Abhinandan.Ecommerce.Entity.Product;
import com.Abhinandan.Ecommerce.Entity.category;
import com.Abhinandan.Ecommerce.Repository.ProductRepository;
import com.Abhinandan.Ecommerce.Repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class productServiceImpl implements productService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private categoryRepository categoryRepository;
    public productDto addProduct(productDto productDto) throws IOException {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());
        category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);

        return productRepository.save(product).getDto();
    }

    public List<productDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

//    public List<Product> getAllProducts(){
//        List<Product> products = productRepository.findAll();
//        return products;
//    }
}
