package com.hkinron.rentalsystem.backend.service.impl;

import com.hkinron.rentalsystem.backend.entity.Tenant;
import com.hkinron.rentalsystem.backend.repository.UserRepository;
import com.hkinron.rentalsystem.backend.service.TenantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

    private UserRepository userRepository;

    public TenantServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Tenant createUser(Tenant tenant) {
        //1.Check if user exist in DB
        Optional<Tenant> userOptional = userRepository.findByName(tenant.getName());
        //2. If exist, update user id
        userOptional.ifPresent(value -> tenant.setId(value.getId()));
        //3. Create or Update user in DB
        return userRepository.save(tenant);
    }

    @Override
    public Tenant getUserById(long id) {
        Optional<Tenant> userOptional = userRepository.findById(id);
        return userOptional.orElse(new Tenant());
    }

    @Override
    public Page<Tenant> getAllUsers(Pageable pageable) {
        Page<Tenant> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }
}
