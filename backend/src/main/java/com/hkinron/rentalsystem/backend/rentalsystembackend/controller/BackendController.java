package com.hkinron.rentalsystem.backend.rentalsystembackend.controller;

import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.Record;
import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.Room;
import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.User;
import com.hkinron.rentalsystem.backend.rentalsystembackend.repository.RecordRepository;
import com.hkinron.rentalsystem.backend.rentalsystembackend.repository.RoomRepository;
import com.hkinron.rentalsystem.backend.rentalsystembackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RecordRepository recordRepository;

    @RequestMapping(path = "/hello")
    @ResponseBody
    public String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public long addNewUser(@RequestBody User user) {
        Room room = user.getRoom();
        userRepository.save(user);
        if (room != null) {
            room.setUser(user);
            LOG.info(room.toString());
            roomRepository.save(room);
        }
        LOG.info(user.toString() + " successfully saved into DB");
        return user.getId();
    }

    @GetMapping(path = "/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

    @GetMapping(path = "/users")
    @ResponseBody
    public List<User> getAllUsers() {
        LOG.info("Reading all users from database.");
        List<User> users = new LinkedList<>();
        userRepository.findAll().forEach(item -> {
                    users.add(item);
                }
        );
        return users;
    }


    @RequestMapping(path = "/room", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public long addNewRoom(@RequestBody Room room) {
        List<Room> rooms = roomRepository.findByName(String.valueOf(room.getName()));
        if (rooms.size() != 0) {
            room.setId(rooms.get(0).getId());
        }

        roomRepository.save(room);

        LOG.info("Room " + room.toString() + " successfully saved into DB");

        return room.getId();
    }

    @GetMapping(path = "/room/{id}")
    @ResponseBody
    public Room getRoomById(@PathVariable("id") long id) {
        LOG.info("Reading record with id " + id + " from database.");
        return roomRepository.findById(id).get();
    }

    @GetMapping(path = "/rooms")
    @ResponseBody
    public List<Room> getAllRooms() {
        LOG.info("Reading all rooms from database.");
        List<Room> rooms = new LinkedList<>();
        roomRepository.findAll().forEach(item -> {
                    rooms.add(item);
                }
        );
        return rooms;
    }

    @RequestMapping(path = "/record", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<Long, String> addNewRecord(@RequestBody List<Record> records ) {
        Map<Long, String> idsmap = new HashMap<>();
        if (records.size() != 0) {
            recordRepository.saveAll(records).forEach(item -> {
                idsmap.put(item.getId(), item.getRoom().getName());
            });
        }

        return idsmap;
    }

    @GetMapping(path = "/record/{id}")
    @ResponseBody
    public Record getRecordById(@PathVariable("id") long id) {
        LOG.info("Reading record with id " + id + " from database.");
        return recordRepository.findById(id).get();
    }

    @GetMapping(path = "/records")
    @ResponseBody
    public List<Record> getAllRecords() {
        LOG.info("Reading all records from database.");
        List<Record> records = new LinkedList<>();
        recordRepository.findAll().forEach(item -> {
                    records.add(item);
                }
        );
        return records;
    }

}
