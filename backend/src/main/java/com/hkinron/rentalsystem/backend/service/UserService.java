package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.domain.User;
import com.hkinron.rentalsystem.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User user) {
        //1.Check if user exist in DB
        Optional<User> userOptional = userRepository.findByName(user.getName());
        //2. If exist, update user id
        userOptional.ifPresent(value -> user.setId(value.getId()));
        //3. Create or Update user in DB
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(new User());
    }

    public Page<User> getAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
