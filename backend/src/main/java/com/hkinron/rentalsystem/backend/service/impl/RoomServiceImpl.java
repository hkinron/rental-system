package com.hkinron.rentalsystem.backend.service.impl;

import com.hkinron.rentalsystem.backend.model.Room;
import com.hkinron.rentalsystem.backend.repository.RoomRepository;
import com.hkinron.rentalsystem.backend.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addNewRoon(Room room) {

        //1. Check room if exist
        Optional<Room> roomInDb = roomRepository.findByName(room.getName());
        //2. If it exist, update room id
        roomInDb.ifPresent(value -> room.setId(value.getId()));
        //3. Create Or Update room in DB
        return roomRepository.save(room);

    }

    @Override
    public Room getRoomById(long id) {
        //1. Check room if exist
        Optional<Room> roomInDb = roomRepository.findById(id);
        //2. If it exist, return. If not, return a null object
        return roomInDb.orElse(new Room());
    }

    @Override
    public Page<Room> getRooms(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public void deleteRoomById(long id) {
        roomRepository.deleteById(id);
    }
}
