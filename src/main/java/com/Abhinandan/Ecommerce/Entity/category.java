    package com.Abhinandan.Ecommerce.Entity;

    import com.Abhinandan.Ecommerce.Enum.CategoryType;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    import lombok.Data;

    @Entity
    @Table(name="category")
    @Data
    public class category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty("id")
        private long CategoryId;

//        @Enumerated(EnumType.STRING)
        @JsonProperty("type")
        private CategoryType type;

        @JsonProperty("name")
        private String name;

        @Lob
        @JsonProperty("description")
        private String description;
    }
