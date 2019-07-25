package com.hkinron.rentalsystem.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@Table(name = "record")
@Data
public class Record implements Comparable<Record>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @JsonFormat(pattern="yyyy-MM")
    private YearMonth yearMonth;
    private int water;
    private int electric;

    protected Record(){}

    public Record (YearMonth yearMonth, int water, int electric){
        this.yearMonth = yearMonth;
        this.water = water;
        this.electric = electric;
    }

    @Override
    public int compareTo(Record record) {

        //Compare yearMonth first
        if(this.yearMonth.compareTo(record.yearMonth) > 0 ){
            return 1;
        }

        if(this.yearMonth.compareTo(record.yearMonth) < 0){
            return -1;
        }

        //Compare room
        if(this.room.compareTo(record.room) > 0 ){
            return 1;
        }

        if(this.room.compareTo(record.room) < 0){
            return -1;
        }

        return 0;
    }
}
