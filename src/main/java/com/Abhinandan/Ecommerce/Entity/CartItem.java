package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Dto.cartItemsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@ToString(exclude = {"user" , "order"})
@Table(name="cartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double price;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_email", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Orders order;

    public cartItemsDto getCartDto(){
        cartItemsDto cartItemsDto = new cartItemsDto();
        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setProductId(product.getProductId());
        cartItemsDto.setEmail(user.getEmail());
        cartItemsDto.setProductName(product.getProductName());
        cartItemsDto.setReceivedImg(product.getImg());

        return cartItemsDto;
    }
}
