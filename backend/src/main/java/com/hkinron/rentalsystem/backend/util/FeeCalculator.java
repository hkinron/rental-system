package com.hkinron.rentalsystem.backend.util;

import com.hkinron.rentalsystem.backend.model.Bill;
import com.hkinron.rentalsystem.backend.model.Record;

public class FeeCalculator {

    public static Bill calculate(Record newRecord, Record lastRecord) {
        int diffWater = newRecord.getWater() - lastRecord.getWater();
        int diffElectric = newRecord.getElectric() - lastRecord.getElectric();
        double waterFee = diffWater * FeeConstants.WATER_FEE;
        double electricFee = diffElectric * FeeConstants.ELECTRIC_FEE;
        int roomFee = newRecord.getRoom().getPrice();
        double sum = waterFee + electricFee + roomFee;
        return new Bill(newRecord, lastRecord, sum);
    }
}
