package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Dto.profileDto;
import com.Abhinandan.Ecommerce.Entity.Orders;
import com.Abhinandan.Ecommerce.Entity.User;
import com.Abhinandan.Ecommerce.Enum.AccountStatus;
import com.Abhinandan.Ecommerce.Repository.UserRepository;
import com.Abhinandan.Ecommerce.Repository.orderRepository;
import com.Abhinandan.Ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private orderRepository orderRepository;

    @Override
    public User saveUser(User user) {

        Orders order = new Orders();

        order.setUser(user);
        orderRepository.save(order);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findById(email);
    }

    @Override
    public boolean deleteUser(String email) {
        Optional<User> user = userRepository.findById(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> updateAccountStatus(String email, User userDetails) {
        return userRepository.findById(email).map(user -> {
            if (user.getAccountStatus().equals(AccountStatus.ACTIVE)) {
                user.setAccountStatus(AccountStatus.BLOCKED);
            } else {
                user.setAccountStatus(AccountStatus.ACTIVE);
            }
            return userRepository.save(user); // Save updated use
        });
    }

    @Override
    public profileDto updateUserProfile(String email, profileDto profile) throws IOException {
        User user = userRepository.findByEmail(email);
        if (profile.getName() != null) {
            user.setName(profile.getName());
        }
        if (profile.getMobileNumber() != 0) {
            user.setMobileNumber(profile.getMobileNumber());
        }
        if (profile.getName() != null) {
            user.setName(profile.getName());
        }
        user.setImg(profile.getProfileImg().getBytes());
        return userRepository.save(user).getDto();
    }

    public boolean changePassword(){
        return true;
    }

}
