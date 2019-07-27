package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.domain.Bill;
import com.hkinron.rentalsystem.backend.domain.Record;
import com.hkinron.rentalsystem.backend.domain.Room;
import com.hkinron.rentalsystem.backend.domain.User;
import com.hkinron.rentalsystem.backend.repository.RecordRepository;
import com.hkinron.rentalsystem.backend.repository.RoomRepository;
import com.hkinron.rentalsystem.backend.repository.UserRepository;
import com.hkinron.rentalsystem.backend.util.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.*;


@RestController
@RequestMapping("/api")
public class BackendController {

    private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private RecordRepository recordRepository;

    public BackendController(UserRepository userRepository, RoomRepository roomRepository, RecordRepository recordRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.recordRepository = recordRepository;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewUser(@RequestBody User user) {

        // If the use exist in DB, update the user in DB
        List<User> userInDB = userRepository.findByName(user.getName());
        if (userInDB.size() != 0) {
            user.setId(userInDB.get(0).getId());
        }
        userRepository.save(user);

        // If the room is not null, update the room in DB
        Room room = user.getRoom();
        if (room != null) {
            room.setUser(user);
            logger.info(room.toString());
            roomRepository.save(room);
        }
        logger.info(user.toString() + " successfully saved into DB");
        return user.getId();
    }

    @DeleteMapping(path = "/user")
    public long deleteUser(@RequestParam String userId) {

        User userInDB = userRepository.findById(Long.parseLong(userId)).orElse(null);

        if (userInDB != null) {
            Room room = userInDB.getRoom();
            // If the room is not null, update the room in DB first
            if (room != null) {
                room.setUser(null);
                logger.info(room.toString());
                roomRepository.save(room);
            }
            userRepository.delete(userInDB);
            logger.info(userInDB + " successfully deleted from DB");
            return userInDB.getId();
        } else {
            logger.info("Cannot find user from DB by userId " + userId);
            return -1L;
        }
    }

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id).orElse(null);
        logger.info("Get user " + user + " from DB by id " + id);
        return user;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        userRepository.findAll().forEach(users::add
        );
        Collections.sort(users);
        logger.info("Get " + users.size() + " users from DB");
        return users;
    }

    @RequestMapping(path = "/records", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<Long, String> addNewRecord(@RequestBody List<Record> records) {

        Map<Long, String> idsmap = new HashMap<>();
        if (records.size() == 0) {
            return idsmap;
        }
        List<Record> recordsInDB = recordRepository.findByYearMonth(records.get(0).getYearMonth());
        List<Record> targetRecords = new LinkedList<>();

        records.forEach(item -> {
            for (Record recordInDB : recordsInDB) {
                // if having the same room between records and recordsInDB, update records id
                if (recordInDB.getRoom().equals(item.getRoom())) {
                    item.setId(recordInDB.getId());
                }
            }

            //Filter out useless records
            if (item.getElectric() == 0 | item.getWater() == 0) {
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
        logger.info("Reading record with id " + id + " from database.");
        return recordRepository.findById(id).get();
    }

    @GetMapping(path = "/records")
    @ResponseBody
    public List<Record> getRecordsInYeahMonth(@RequestParam YearMonth yearMonth) {
        if (yearMonth != null) {
            logger.info("Reading records in yearMonth " + yearMonth + " from database.");
            return recordRepository.findByYearMonth(yearMonth);
        } else {
            logger.info("Reading all records from database.");
            List<Record> records = new LinkedList<>();
            recordRepository.findAll().forEach(item -> {
                        records.add(item);
                    }
            );

            Collections.sort(records);
            return records;
        }

    }

    @GetMapping(path = "/bills")
    @ResponseBody
    public List<Bill> getRecordsByYearMonth(@RequestParam YearMonth yearMonth) {
        logger.info("Reading records with YearMonth " + yearMonth + " from database.");
        List<Record> nowRecords = recordRepository.findByYearMonth(yearMonth);
        List<Record> lastRecords = recordRepository.findByYearMonth(yearMonth.minusMonths(1));
        Collections.sort(nowRecords);
        Collections.sort(lastRecords);
        List<Bill> bills = new LinkedList<>();

        for (Record nowRecord : nowRecords) {
            Room nowRoom = nowRecord.getRoom();
            lastRecords.forEach(item -> {
                if (item.getRoom().equals(nowRoom)) {
                    Bill bill = Calculator.calculate(nowRecord, item);
                    ((LinkedList<Bill>) bills).add(bill);

                }
            });

        }
        return bills;
    }

}
