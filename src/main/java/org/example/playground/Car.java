package org.example.playground;

import lombok.Data;

@Data
public class Car {

    private String make;

    private int numberOfSeats;

    private CarType type;
}
