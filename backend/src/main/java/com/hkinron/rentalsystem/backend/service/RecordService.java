package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.domain.Record;
import com.hkinron.rentalsystem.backend.repository.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Iterable<Record> addNewRecords(List<Record> records) {
        Pageable pageable = PageRequest.of(0, -1);
        Page<Record> recordsPage = recordRepository.findByYearMonth(records.get(0).getYearMonth(), pageable);
        recordsPage.forEach(recordInDB -> {
            if (records.contains(recordInDB)) {
                Record record = records.get(records.indexOf(recordInDB));
                record.setId(recordInDB.getId());
            }
        });
        return recordRepository.saveAll(records);
    }

    public Page<Record> getRecordsInYeahMonth(YearMonth yearMonth, Pageable pageable) {
        return recordRepository.findByYearMonth(yearMonth, pageable);
    }

    public void deleteRecordsById(long id) {
        recordRepository.deleteById(id);
    }
}
