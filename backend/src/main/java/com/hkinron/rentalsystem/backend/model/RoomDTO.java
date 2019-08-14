package com.hkinron.rentalsystem.backend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomDTO {
    private UUID id;
    private String name;
    private int price;
}
