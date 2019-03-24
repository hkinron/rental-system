package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.domain.Record;
import org.springframework.data.repository.CrudRepository;

import java.time.YearMonth;
import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {
    List<Record> findByYearMonth(YearMonth yearMonth);
    List<Record> findByRoomId(Long roomId);
}
