package com.Abhinandan.Ecommerce.Dto;

import com.Abhinandan.Ecommerce.Enum.orderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class orderDto {
    private long orderId;

    private Date date;

    private double amountPaid;

    private double totalAmount;

    private double discount;

    private String address;

    private orderStatus orderStatus;

    private UUID trackingId;

    private String email;

    private List<cartItemsDto> cartItems;
}
