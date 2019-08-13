package com.hkinron.rentalsystem.backend.repository;

import com.hkinron.rentalsystem.backend.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {
    Page<Record> findByYearMonth(YearMonth yearMonth, Pageable pageable);

    Optional<List<Record>> findByYearMonth(YearMonth yearMonth);
}
