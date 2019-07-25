package com.hkinron.rentalsystem.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tenant")
@Data
public class User implements Comparable<User> {

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

    @Override
    public int compareTo(User user) {

        //Compare name first
        if(this.name.compareTo(user.getName()) > 0 ){
            return 1;
        }

        if(this.name.compareTo(user.getName()) < 0 ){
            return -1;
        }

        //Compare Room
        if(this.room != null && user.getRoom() != null ){
            if(this.room.compareTo(user.getRoom()) > 0){
                return 1;
            }
            if(this.room.compareTo(user.getRoom()) < 0){
                return -1;
            }
        }

        return 0;
    }
}
