package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.entity.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    Optional<Room> findByName( String name);
}
