package com.hkinron.rentalsystem.backend.rentalsystembackend.controller;

import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.Record;
import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.Room;
import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.User;
import com.hkinron.rentalsystem.backend.rentalsystembackend.repository.RecordRepository;
import com.hkinron.rentalsystem.backend.rentalsystembackend.repository.RoomRepository;
import com.hkinron.rentalsystem.backend.rentalsystembackend.repository.UserRepository;
import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;


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
    public long addNewUser(@RequestParam String name, @RequestParam String phone, @RequestParam String roomId){

        User user = new User(name, phone);
        Room room = null;
        if( roomId != null && roomId != ""){
            LOG.info("roomId: " + roomId);
            room = roomRepository.findById(Long.valueOf(roomId)).get();
            LOG.info(room.toString());
            room.setUser(user);
            user.setRoom(room);
        }
        userRepository.save(user);
        if(room != null){
            roomRepository.save(room);
        }
        LOG.info(user.toString() + " successfully saved into DB");
        return user.getId();
    }

    @GetMapping(path="/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

    @GetMapping(path="/users")
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
    public long addNewRoom(@RequestParam String name, @RequestParam int price){
        List<Room> rooms = roomRepository.findByName(String.valueOf(name));
        Room room = null;
        if( rooms.size()!= 0){
            room = rooms.get(0);
            room.setPrice(price);
        }else {
            room = new Room(name, price);
        }

        roomRepository.save(room);

        LOG.info("Room " + room.toString() + " successfully saved into DB");

        return room.getId();
    }

    @GetMapping(path="/room/{id}")
    @ResponseBody
    public Room getRoomById(@PathVariable("id") long id) {
        LOG.info("Reading record with id " + id + " from database.");
        return roomRepository.findById(id).get();
    }

    @GetMapping(path="/rooms")
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
    public long addNewRecord(@RequestParam YearMonth yearMonth, @RequestParam int water, @RequestParam int electric, @RequestParam Long roomId){
        List<Record> records = recordRepository.findByYearMonth(yearMonth);
        Record record = null;
        if( records.size()!= 0){
            //TODO
        }else {
            record = new Record(yearMonth, water, electric);
        }

        recordRepository.save(record);

        LOG.info("Record " + record.toString() + " successfully saved into DB");

        return record.getId();
    }

    @GetMapping(path="/record/{id}")
    @ResponseBody
    public Record getRecordById(@PathVariable("id") long id) {
        LOG.info("Reading record with id " + id + " from database.");
        return recordRepository.findById(id).get();
    }

    @GetMapping(path="/records")
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
