package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.domain.Room;
import com.hkinron.rentalsystem.backend.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping(path = "/room")
    public Room addNewRoom(@RequestBody Room room) {
        Room roomInDb = new Room();
        try {
            roomInDb = roomService.addNewRoon(room);
        } catch (Exception e) {
            logger.error(String.format("Fail to add new room: %s", room));
        }
        return roomInDb;
    }


    @GetMapping(path = "/room/{id}")
    @ResponseBody
    public Room getRoomById(@PathVariable("id") long id) {
        Room roomInDb = roomService.getRoomById(id);
        logger.info(String.format("Successfully get %s id %d from database.", roomInDb ,id));
        return roomInDb;
    }

    @GetMapping(path = "/rooms")
    @ResponseBody
    public List<Room> getAllRooms(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        List<Room> roomsInDb = roomService.getRooms(pageable);
        logger.info("Reading all rooms from database.");
        return roomsInDb;
    }

    @DeleteMapping(path = "/room/{id}")
    @ResponseBody
    public void deleteRoomById(@PathVariable("id") long id) {
        roomService.deleteRoomById(id);
        logger.info(String.format("Successfully delete room which id is %d from database.", id));
    }

}
