package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository <Room, Long> {
    List<Room> findByName(@Param("name") String name);
    List<Room> findByPrice(@Param("price") int price);
}