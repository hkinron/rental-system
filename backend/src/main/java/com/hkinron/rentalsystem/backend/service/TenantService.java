package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.entity.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TenantService {

    public Tenant createUser(Tenant tenant);

    public Tenant getUserById(long id) ;

    public Page<Tenant> getAllUsers(Pageable pageable);

    public void deleteUserById(long userId);
}
