package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Dto.productDto;
import com.Abhinandan.Ecommerce.Entity.Product;

import java.io.IOException;
import java.util.List;

public interface productService {
    productDto addProduct(productDto productDto) throws IOException;
    List<productDto> getAllProducts();
}
