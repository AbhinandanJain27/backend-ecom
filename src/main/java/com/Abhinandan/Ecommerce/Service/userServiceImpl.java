package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Entity.User;
import com.Abhinandan.Ecommerce.Enum.AccountStatus;
import com.Abhinandan.Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
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
            if(user.getAccountStatus().equals(AccountStatus.ACTIVE)){
                user.setAccountStatus(AccountStatus.BLOCKED);
            }else {
                user.setAccountStatus(AccountStatus.ACTIVE);
            }
            return userRepository.save(user); // Save updated use
        });
    }


}
