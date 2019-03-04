package com.hkinron.rentalsystem.backend.rentalsystembackend.domain;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String phone;

    protected User() {}

    public User (String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s', phone='%s']",
                id, name, phone);
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
}
