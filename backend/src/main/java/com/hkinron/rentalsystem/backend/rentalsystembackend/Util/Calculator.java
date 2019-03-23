package com.hkinron.rentalsystem.backend.rentalsystembackend.Util;

import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.Bill;
import com.hkinron.rentalsystem.backend.rentalsystembackend.domain.Record;

public class Calculator {

    public static Bill calculate(Record newRecord, Record lastRecord){
        int diffWater = newRecord.getWater() - lastRecord.getWater();
        int diffElectric = newRecord.getElectric() - lastRecord.getElectric();
        double waterFee = diffWater * Fee.WATERFEE.getFee();
        double electricFee = diffElectric * Fee.ELECTRICFEE.getFee();
        int roomFee = newRecord.getRoom().getPrice();
        double sum = waterFee + electricFee + roomFee;
        return new Bill(newRecord, lastRecord, sum);
    }
}
