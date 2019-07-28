package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.domain.Bill;
import com.hkinron.rentalsystem.backend.domain.Record;
import com.hkinron.rentalsystem.backend.domain.Room;
import com.hkinron.rentalsystem.backend.repository.RecordRepository;
import com.hkinron.rentalsystem.backend.util.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class BackendController {

    private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

    private RecordRepository recordRepository;

    public BackendController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }


    @GetMapping(path = "/bills")
    @ResponseBody
    public List<Bill> getRecordsByYearMonth(@RequestParam YearMonth yearMonth, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
            Pageable pageable) {
        logger.info("Reading records with YearMonth " + yearMonth + " from database.");
        Page<Record> nowRecords = recordRepository.findByYearMonth(yearMonth, pageable);
        Page<Record> lastRecords = recordRepository.findByYearMonth(yearMonth.minusMonths(1), pageable);
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
