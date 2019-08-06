package com.hkinron.rentalsystem.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tenant")
@Data
public class Tenant implements Comparable<Tenant> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @JsonIgnoreProperties("tenants")
    private Room room;

    public Tenant() {
    }

    public Tenant(String name, String phone, Room room) {
        this.name = name;
        this.phone = phone;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", roomName=" + room.getName() +
                '}';
    }

    @Override
    public int compareTo(Tenant tenant) {
        //Compare name first
        if (this.name.compareTo(tenant.getName()) != 0) {
            return this.name.compareTo(tenant.getName());
        } else if (this.room != null && tenant.getRoom() != null) {
            //Compare Room
            return this.room.compareTo(tenant.getRoom());
        } else {
            return 0;
        }

    }
}
