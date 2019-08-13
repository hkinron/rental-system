package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.model.Bill;
import com.hkinron.rentalsystem.backend.entity.Record;
import com.hkinron.rentalsystem.backend.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/records")
public class RecordController {
    private RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping(path = "/")
    public List<Long> createRecords(@RequestBody List<Record> records) {
        Iterable<Record> recordsIterator = recordService.createRecords(records);
        List<Long> recordIds = new LinkedList<>();
        recordsIterator.forEach(record -> recordIds.add(record.getId()));
        log.info(String.format("Successfully update records: %s", recordIds));
        return recordIds;
    }

    @GetMapping(path = "/yearMonth/{yeahMonth}")
    public Page<Record> getRecordsByYeahMonth(@PathVariable("yeahMonth") YearMonth yearMonth,
                                              @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                                      Pageable pageable) {
        Page<Record> recordsInYeahMonth = recordService.getRecordsInYeahMonth(yearMonth, pageable);
        log.info(String.format("Successfully get records: %s", recordsInYeahMonth));
        return recordsInYeahMonth;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRecordById(@PathVariable("id") long id) {
        recordService.deleteRecordById(id);
        log.info(String.format("Successfully delete record by id: %d", id));
    }

    @GetMapping(path = "/bills")
    public List<Bill> getBillsByYearMonth(@RequestParam YearMonth yearMonth, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
            Pageable pageable) {
        List<Bill> bills = recordService.getBillByYearMonth(yearMonth, pageable);
        log.info(String.format("Successfully get  bills: %s", bills));
        return bills;
    }

}
