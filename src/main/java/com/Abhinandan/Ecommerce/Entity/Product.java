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
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("quantity")
    private long quantity;

    @Lob
    @JsonProperty("description")
    private String Description;

    @Lob
    @JsonProperty("img")
    @Column(columnDefinition = "longblob")
    private byte[] img;

    // Multiple Products can have same categories
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private category category;

    public productDto getDto() {
        productDto productDto = new productDto();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setQuantity(quantity);
        productDto.setDescription(Description);
        productDto.setByteImg(img);
        productDto.setCategoryId(category.getCategoryId());
        productDto.setCategoryName(category.getName());
        return productDto;
    }
}