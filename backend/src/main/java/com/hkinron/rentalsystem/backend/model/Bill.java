package com.hkinron.rentalsystem.backend.model;

import com.hkinron.rentalsystem.backend.entity.Record;
import lombok.Data;

@Data
public class Bill {

    private Record nowRecord;

    private Record lastRecord;

    private double sum;

    public Bill(Record nowRecord, Record lastRecord, double sum){
        this.nowRecord = nowRecord;
        this.lastRecord = lastRecord;
        this.sum = sum;
    }

    protected Bill(){}
}
