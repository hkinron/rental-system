package com.hkinron.rentalsystem.backend.service.impl;

import com.hkinron.rentalsystem.backend.model.Bill;
import com.hkinron.rentalsystem.backend.entity.Record;
import com.hkinron.rentalsystem.backend.entity.Room;
import com.hkinron.rentalsystem.backend.repository.RecordRepository;
import com.hkinron.rentalsystem.backend.service.RecordService;
import com.hkinron.rentalsystem.backend.util.FeeCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Iterable<Record> createRecords(List<Record> records) {
        Optional<List<Record>> recordListOptional = recordRepository.findByYearMonth(records.get(0).getYearMonth());
        recordListOptional.ifPresent(recordList -> recordList.forEach(recordInDb -> {
            if (records.contains(recordInDb)) {
                Record record = records.get(records.indexOf(recordInDb));
                record.setId(recordInDb.getId());
            }
        }));
        return recordRepository.saveAll(records);
    }

    @Override
    public Page<Record> getRecordsInYeahMonth(YearMonth yearMonth, Pageable pageable) {
        return recordRepository.findByYearMonth(yearMonth, pageable);
    }

    @Override
    public void deleteRecordById(long id) {
        recordRepository.deleteById(id);
    }

    @Override
    public List<Bill> getBillByYearMonth(YearMonth yearMonth, Pageable pageable) {

        log.info("Reading records with YearMonth " + yearMonth + " from database.");
        Page<Record> nowRecords = recordRepository.findByYearMonth(yearMonth, pageable);
        Page<Record> lastRecords = recordRepository.findByYearMonth(yearMonth.minusMonths(1), pageable);
        LinkedList<Bill> bills = new LinkedList<>();

        for (Record nowRecord : nowRecords) {
            Room nowRoom = nowRecord.getRoom();
            lastRecords.forEach(item -> {
                if (item.getRoom().equals(nowRoom)) {
                    Bill bill = FeeCalculator.calculate(nowRecord, item);
                    bills.add(bill);

                }
            });

        }
        return bills;
    }

}
