package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByName(@Param("name") String name);
}
