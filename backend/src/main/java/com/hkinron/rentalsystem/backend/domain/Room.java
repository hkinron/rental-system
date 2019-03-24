package com.hkinron.rentalsystem.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "room")
public class Room implements Comparable<Room> {
    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String name;
    private int price;

    /**
     * Typically, we think of many-to-many relationships when we consider a join table, but, using a join table, in this case, can help us to eliminate these null values:
     * Room is the owner of this relationship as we chose to use the join table annotation on it
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "room_tenant",
            joinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "id", unique = true)  },
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    @JsonManagedReference
    private User user;

    protected Room() {}

    public Room(String name, int price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Room[id=%d, name='%s', price='%s', user='%s']",
                id, name, price, user == null?null:user.getName());
    }

    public Long getId() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(Room room) {
        //Compare name
        if(this.name.compareTo(room.getName()) > 0){
            return 1;
        }
        if(this.name.compareTo(room.getName()) < 0){
            return -1;
        }

        //Compare id;
        if(this.id.compareTo(room.getId()) > 0){
            return 1;
        }
        if(this.id.compareTo(room.getId()) < 0){
            return -1;
        }


        return 0;
    }
}
