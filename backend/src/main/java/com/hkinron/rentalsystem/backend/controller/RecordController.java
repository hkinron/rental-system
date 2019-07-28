package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.domain.Record;
import com.hkinron.rentalsystem.backend.service.RecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;

@RestController
public class RecordController {
    private RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping(path = "/records")
    public List<Long> addNewRecord(@RequestBody List<Record> records) {
        Iterable<Record> recordsIterator = recordService.addNewRecords(records);
        List<Long> recordIds = new LinkedList<>();
        recordsIterator.forEach(record -> {
            recordIds.add(record.getId());
        });
        return recordIds;
    }

    @GetMapping(path = "/records")
    public Page<Record> getRecordsInYeahMonth(@RequestParam YearMonth yearMonth,
                                              @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                                      Pageable pageable) {
        return recordService.getRecordsInYeahMonth(yearMonth, pageable);
    }

    @DeleteMapping(path = "/record/{id}")
    public void deleteRecordsById(@RequestParam long id) {
        recordService.deleteRecordsById(id);
    }

}
