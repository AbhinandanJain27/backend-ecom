package com.Abhinandan.Ecommerce.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class productDto {

    private long productId;
    private String productName;
    private Double price;
    private long quantity;
    private String description;
    private byte[] byteImg;
    private long categoryId;
    private String categoryName;
    private MultipartFile img;
}
