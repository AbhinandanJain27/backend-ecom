package com.Abhinandan.Ecommerce.Dto;

import lombok.Data;

@Data
public class cartItemsDto {
    private long id;
    private double price;
    private Long quantity;
    private Long productId;
    private Long orderId;
    private String productName;
    private byte[] receivedImg;
    private String email;
}
