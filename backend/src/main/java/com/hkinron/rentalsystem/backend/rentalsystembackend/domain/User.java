package com.hkinron.rentalsystem.backend.rentalsystembackend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tenant")
public class User {

    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotEmpty
    private String name;
    private String phone;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Room room;

    protected User() {}

    public User (String name, String phone, Room room){
        this.name = name;
        this.phone = phone;
        this.room = room;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s', phone='%s', room='%s']",
                id, name, phone, room==null?null:room.getName());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
