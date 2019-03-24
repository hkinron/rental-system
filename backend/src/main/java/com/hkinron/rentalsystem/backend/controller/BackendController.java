package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.domain.Record;
import com.hkinron.rentalsystem.backend.util.Calculator;
import com.hkinron.rentalsystem.backend.domain.Bill;
import com.hkinron.rentalsystem.backend.domain.Room;
import com.hkinron.rentalsystem.backend.domain.User;
import com.hkinron.rentalsystem.backend.repository.RecordRepository;
import com.hkinron.rentalsystem.backend.repository.RoomRepository;
import com.hkinron.rentalsystem.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.*;


@RestController
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RecordRepository recordRepository;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public long addNewUser(@RequestBody User user) {

        // If the use exist in DB, update the user in DB
        List<User> userInDB = userRepository.findByName(user.getName());
        if(userInDB.size() != 0){
            user.setId(userInDB.get(0).getId());
        }
        userRepository.save(user);

        // If the room is not null, update the room in DB
        Room room = user.getRoom();
        if (room != null ) {
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
        Collections.sort(users);
        return users;
    }


    @RequestMapping(path = "/room", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public long addNewRoom(@RequestBody Room room) {

        //If the room exist in DB, update it
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
        Collections.sort(rooms);
        return rooms;
    }

    @RequestMapping(path = "/records", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<Long, String> addNewRecord(@RequestBody List<Record> records ) {

        Map<Long, String> idsmap = new HashMap<>();
        if (records.size() == 0 ){
            return idsmap;
        }
        List<Record> recordsInDB = recordRepository.findByYearMonth(records.get(0).getYearMonth());
        List<Record> targetRecords = new LinkedList<>();

        records.forEach(item -> {
            for (Record recordInDB : recordsInDB) {
                // if having the same room between records and recordsInDB, update records id
                if(recordInDB.getRoom().equals(item.getRoom())){
                    item.setId(recordInDB.getId());
                }
            }

            //Filter out useless records
            if(item.getElectric() == 0 | item.getWater() == 0 ){
                return;
            }

            ((LinkedList<Record>) targetRecords).push(item);

        });

        if (targetRecords.size() != 0) {
            recordRepository.saveAll(targetRecords).forEach(item -> {
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
    public List<Record> getRecordsInYeahMonth(@RequestParam YearMonth yearMonth) {
        if (yearMonth != null){
            LOG.info("Reading records in yearMonth " + yearMonth + " from database.");
            return recordRepository.findByYearMonth(yearMonth);
        }else{
            LOG.info("Reading all records from database.");
            List<Record> records = new LinkedList<>();
            recordRepository.findAll().forEach(item -> {
                        records.add(item);
                    }
            );

            Collections.sort(records);
            return records;
        }

    }

//    @GetMapping(path = "/records")
//    @ResponseBody
//    public List<Record> getAllRecords() {
//        LOG.info("Reading all records from database.");
//        List<Record> records = new LinkedList<>();
//        recordRepository.findAll().forEach(item -> {
//                    records.add(item);
//                }
//        );
//        return records;
//    }

    @GetMapping(path = "/bills")
    @ResponseBody
    public List<Bill> getRecordsByYearMonth(@RequestParam YearMonth yearMonth) {
        LOG.info("Reading records with YearMonth " + yearMonth + " from database.");
        List<Record> nowRecords = recordRepository.findByYearMonth(yearMonth);
        List<Record> lastRecords = recordRepository.findByYearMonth(yearMonth.minusMonths(1));

        List<Bill> bills = new LinkedList<>();

        for ( Record nowRecord : nowRecords ){
            Room nowRoom = nowRecord.getRoom();
            lastRecords.forEach(item -> {
                if(item.getRoom().equals(nowRoom)){
                    Bill bill = Calculator.calculate(nowRecord, item);
                    ((LinkedList<Bill>) bills).push(bill);

                }
            });

        }
        return bills;
    }

}
