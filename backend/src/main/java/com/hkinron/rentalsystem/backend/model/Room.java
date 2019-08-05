package com.hkinron.rentalsystem.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "room")
@Data
public class Room implements Comparable<Room> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private int price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Tenant> tenant;

    public Room(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Room() {
    }

    @Override
    public int compareTo(Room room) {
        //Compare name
        if (this.name.compareTo(room.name) != 0) {
            return this.name.compareTo(room.name);
        } else {
            //Compare id
            return this.id.compareTo(room.id);
        }
    }
}
