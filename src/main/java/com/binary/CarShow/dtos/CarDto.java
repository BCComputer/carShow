package com.binary.CarShow.dtos;

import com.binary.CarShow.entities.Owner;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    @NotNull(message = "Brand should not be null")
    private String brand;
    @NotNull(message = "Model should not be null")
    private String model;
    @NotNull(message = "Color should not be null")
    private String color;
    @NotNull(message = "Register Number should not be null")
    private String registerNumber;
    @Min(value = 1990, message = "Year should not be null")
    private Integer year;
    @Min(0)
    private double price;
    @NotNull(message = "Make should not be null")
    private String make;
    @NotNull(message = "Owner should not be null")
    private Owner owner;
}
