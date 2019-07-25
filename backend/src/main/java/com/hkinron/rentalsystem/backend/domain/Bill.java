package com.hkinron.rentalsystem.backend.domain;

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
