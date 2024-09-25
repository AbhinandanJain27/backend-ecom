package com.Abhinandan.Ecommerce.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class productDto {

    private Long productId;
    private String productName;
    private Double price;
    private String description;
    private byte[] byteImg;
    private Long categoryId;
    private MultipartFile img;
}
