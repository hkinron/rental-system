package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.model.Tenant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<Tenant, Long> {
    Optional<Tenant> findByName(@Param("name") String name);
}
