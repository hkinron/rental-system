package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository <User, Long> {
    List<User> findByName(@Param("name") String name);
    List<User> findByPhone(@Param("phone") String phone);
}