package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.entity.Room;
import com.hkinron.rentalsystem.backend.exception.ResourceNotFoundException;
import com.hkinron.rentalsystem.backend.exception.RoomOperationException;
import com.hkinron.rentalsystem.backend.model.RoomDTO;
import com.hkinron.rentalsystem.backend.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("rooms")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room createRoom(@RequestBody RoomDTO roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        Room roomInDb = null;
        try {
            roomInDb = roomService.createRoom(room);
        } catch (Exception e) {
            log.error(String.format("Fail to create room: %s", roomDTO));
            throw new RoomOperationException("Fail to create room");
        }
        return roomInDb;
    }

    @GetMapping(path = "/{id}")
    public Room getRoomById(@PathVariable("id") long id) {
        Room roomInDb = roomService.getRoomById(id);
        log.info(String.format("Successfully get %s id %d.", roomInDb, id));
        return roomInDb;
    }

    @GetMapping
    public Page<Room> getAllRooms(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<Room> roomsInDb = roomService.getRooms(pageable);
        log.info(String.format("Reading %d rooms.", roomsInDb.getTotalElements()));
        return roomsInDb;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRoomById(@PathVariable("id") long id) {
        roomService.deleteRoomById(id);
        log.info(String.format("Successfully delete room which id is %d.", id));
    }

}
