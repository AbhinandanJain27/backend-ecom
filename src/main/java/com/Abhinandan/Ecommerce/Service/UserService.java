package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Dto.profileDto;
import com.Abhinandan.Ecommerce.Entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> findByEmail(String email);
    boolean deleteUser(String email);
    Optional<User> updateAccountStatus(String email, User userDetails);
    profileDto updateUserProfile(String email, profileDto profile) throws IOException;
}
