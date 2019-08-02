package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.model.Bill;
import com.hkinron.rentalsystem.backend.model.Record;
import com.hkinron.rentalsystem.backend.model.Room;
import com.hkinron.rentalsystem.backend.repository.RecordRepository;
import com.hkinron.rentalsystem.backend.util.FeeCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
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

    public List<Bill> getBillByYearMonth(YearMonth yearMonth, Pageable pageable){

        log.info("Reading records with YearMonth " + yearMonth + " from database.");
        Page<Record> nowRecords = recordRepository.findByYearMonth(yearMonth, pageable);
        Page<Record> lastRecords = recordRepository.findByYearMonth(yearMonth.minusMonths(1), pageable);
        List<Bill> bills = new LinkedList<>();

        for (Record nowRecord : nowRecords) {
            Room nowRoom = nowRecord.getRoom();
            lastRecords.forEach(item -> {
                if (item.getRoom().equals(nowRoom)) {
                    Bill bill = FeeCalculator.calculate(nowRecord, item);
                    ((LinkedList<Bill>) bills).add(bill);

                }
            });

        }
        return bills;
    }

}
