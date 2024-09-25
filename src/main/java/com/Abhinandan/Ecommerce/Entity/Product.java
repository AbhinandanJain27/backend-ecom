package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Dto.productDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("productId")
    private long productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("price")
    private double price;
    @Lob
    @JsonProperty("description")
    private String Description;
    @Lob
    @JsonProperty("productId")
    @Column(columnDefinition = "longblob")
    private byte[] img;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private category category;

    public productDto getDto() {
        productDto productDto = new productDto();
        productDto.setProductId(productId);
        productDto.setProductName(productName);
        productDto.setPrice(price);
        productDto.setDescription(Description);
        productDto.setByteImg(img);
        productDto.setCategoryId(category.getCategoryId());
        return productDto;
    }
}