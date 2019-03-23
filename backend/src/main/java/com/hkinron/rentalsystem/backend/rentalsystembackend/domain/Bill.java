package com.hkinron.rentalsystem.backend.rentalsystembackend.domain;

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

    public Record getNowRecord() {
        return nowRecord;
    }

    public void setNowRecord(Record nowRecord) {
        this.nowRecord = nowRecord;
    }

    public Record getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(Record lastRecord) {
        this.lastRecord = lastRecord;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
