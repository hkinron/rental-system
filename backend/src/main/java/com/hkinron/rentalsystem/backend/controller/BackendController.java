package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.domain.Bill;
import com.hkinron.rentalsystem.backend.domain.Record;
import com.hkinron.rentalsystem.backend.domain.Room;
import com.hkinron.rentalsystem.backend.repository.RecordRepository;
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

    private RecordRepository recordRepository;

    public BackendController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
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
