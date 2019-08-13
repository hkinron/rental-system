package com.hkinron.rentalsystem.backend.service;

import com.hkinron.rentalsystem.backend.model.Bill;
import com.hkinron.rentalsystem.backend.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public interface RecordService {

    public Iterable<Record> createRecords(List<Record> records);

    public Page<Record> getRecordsInYeahMonth(YearMonth yearMonth, Pageable pageable);

    public void deleteRecordById(long id);

    public List<Bill> getBillByYearMonth(YearMonth yearMonth, Pageable pageable);

}
