package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Enum.AccountStatus;
import com.Abhinandan.Ecommerce.Enum.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "users")
public class User {
    @jakarta.persistence.Id
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String Name;
    @JsonProperty("mobileNumber")
    private long mobileNumber;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private UserRole role;
    @JsonProperty("accountStatus")
    private AccountStatus accountStatus;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    public User(){}

    public User(String name, String email, long mobileNumber, String password, AccountStatus accountStatus) {
        Name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }
}
