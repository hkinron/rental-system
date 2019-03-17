package com.hkinron.rentalsystem.backend.rentalsystembackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "room")
public class Room {
    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(unique = true)
    @NotEmpty
    private String name;
    private String price;

    /**
     * Typically, we think of many-to-many relationships when we consider a join table, but, using a join table, in this case, can help us to eliminate these null values:
     * Room is the owner of this relationship as we chose to use the join table annotation on it
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "room_user",
            joinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "id", unique = true)  },
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    @JsonManagedReference
    private User user;

    protected Room() {}

    public Room(String name, String price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Room[id=%d, name='%s', price='%s', user='%s']",
                id, name, price, user == null?null:user.getName());
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
