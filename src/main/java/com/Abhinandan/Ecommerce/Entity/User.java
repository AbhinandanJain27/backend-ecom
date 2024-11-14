package com.Abhinandan.Ecommerce.Entity;

import com.Abhinandan.Ecommerce.Dto.profileDto;
import com.Abhinandan.Ecommerce.Enum.AccountStatus;
import com.Abhinandan.Ecommerce.Enum.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

//    @Enumerated(EnumType.STRING)
    @JsonProperty("role")
    private UserRole role;

//    @Enumerated(EnumType.STRING)
    @JsonProperty("accountStatus")
    private AccountStatus accountStatus;

    @Lob
    @JsonProperty("img")
    @Column(columnDefinition = "longblob")
    private byte[] img;

    public User(){}

    // Address Class will be added in the end when required in this class as well as the required DTO's
    public profileDto getDto(){
        profileDto profile = new profileDto();
        profile.setEmail(email);
        profile.setName(Name);
        profile.setPassword(password);
        profile.setMobileNumber(mobileNumber);
        profile.setByteImg(img);
        return profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_"+role.name());
    }

    @Override
    public String getUsername() {
        return email;
    }

}
