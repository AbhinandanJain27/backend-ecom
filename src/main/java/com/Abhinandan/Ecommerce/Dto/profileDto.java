package com.Abhinandan.Ecommerce.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class profileDto {
    private String email;
    private String name;
    private long mobileNumber;
    private String password;
    private MultipartFile profileImg;
    private byte[] byteImg;
}
