package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Enum.AccountStatus;
import com.Abhinandan.Ecommerce.Enum.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table (name = "users")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

}
