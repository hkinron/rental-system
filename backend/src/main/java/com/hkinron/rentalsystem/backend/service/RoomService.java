package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

    public Room createRoom(Room room);

    public Room getRoomById(long id);

    public Page<Room> getRooms(Pageable pageable);

    public void deleteRoomById(long id);
}
