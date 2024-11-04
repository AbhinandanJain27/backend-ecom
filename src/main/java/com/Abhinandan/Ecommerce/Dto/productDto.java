package com.Abhinandan.Ecommerce.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class productDto {

    private Long id;
    private String name;
    private Double price;
    private long quantity;
    private String description;
    private byte[] byteImg;
    private Long categoryId;
    private String categoryName;
    private MultipartFile img;

    public void setId(long id) {
    }
}
