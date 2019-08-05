package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.model.Tenant;
import com.hkinron.rentalsystem.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TanentService {

    private UserRepository userRepository;

    public TanentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Tenant addNewUser(Tenant tenant) {
        //1.Check if user exist in DB
        Optional<Tenant> userOptional = userRepository.findByName(tenant.getName());
        //2. If exist, update user id
        userOptional.ifPresent(value -> tenant.setId(value.getId()));
        //3. Create or Update user in DB
        return userRepository.save(tenant);
    }

    public Tenant getUserById(long id) {
        Optional<Tenant> userOptional = userRepository.findById(id);
        return userOptional.orElse(new Tenant());
    }

    public Page<Tenant> getAllUsers(Pageable pageable) {
        Page<Tenant> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
