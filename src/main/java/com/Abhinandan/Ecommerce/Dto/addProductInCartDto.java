package com.Abhinandan.Ecommerce.Dto;

import lombok.Data;

@Data
public class addProductInCartDto {

    private Long orderId;

    private long productId;

    private int quantity;

}
