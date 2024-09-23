package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public Optional<User> findByEmail(String email);
    public boolean deleteUser(String email);
    public Optional<User> updateAccountStatus(String email, User userDetails);
}
