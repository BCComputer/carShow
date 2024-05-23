package com.binary.CarShow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Integer id;
    private String brand;
    private String model;
    private String color;
    private String registerNumber;
    private Integer year;
    private double price;
    private String make;
}
