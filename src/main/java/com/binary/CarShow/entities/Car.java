package com.binary.CarShow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_table")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String brand;
    private String model;
    private String color;
    private String registerNumber;
    private Integer year;
    private double price;
    private String make;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "owner")
    private Owner owner;
}
