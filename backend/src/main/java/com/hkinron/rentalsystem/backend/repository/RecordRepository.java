package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.domain.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.YearMonth;

public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {
    Page<Record> findByYearMonth(YearMonth yearMonth, Pageable pageable);
}
