package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    Optional<Room> findByName( String name);
    Optional<List<Room>> findByPrice( int price);
}
