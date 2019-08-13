package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.model.Tenant;
import com.hkinron.rentalsystem.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TanentService {

    public Tenant addNewUser(Tenant tenant);

    public Tenant getUserById(long id) ;

    public Page<Tenant> getAllUsers(Pageable pageable);

    public void deleteUser(long userId);
}
