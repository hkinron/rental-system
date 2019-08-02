package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.model.Room;
import com.hkinron.rentalsystem.backend.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addNewRoon(Room room) {

        //1. Check room if exist
        Optional<Room> roomInDb = roomRepository.findByName(room.getName());
        //2. If it exist, update room id
        roomInDb.ifPresent(value -> room.setId(value.getId()));
        //3. Create Or Update room in DB
        return roomRepository.save(room);

    }

    public Room getRoomById(long id) {
        //1. Check room if exist
        Optional<Room> roomInDb = roomRepository.findById(id);
        //2. If it exist, return. If not, return a null object
        return roomInDb.orElse(new Room());
    }

    public List<Room> getRooms(Pageable pageable) {
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return null;
    }

    public void deleteRoomById(long id) {
        roomRepository.deleteById(id);
    }
}
