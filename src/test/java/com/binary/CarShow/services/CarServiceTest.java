package com.binary.CarShow.services;

import com.binary.CarShow.entities.Car;
import com.binary.CarShow.repositatories.CarRepository;
import com.binary.CarShow.repositatories.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CarServiceTest {
    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;
    @Mock
    private OwnerRepository ownerRepository;

@Test
@DisplayName("Car controller get all cars success")
public void carService_getAllCars_success() {
    List<Car> cars = new ArrayList<>();
    cars.add(new Car());
    cars.add(new Car());

    when(carRepository.findAll()).thenReturn(cars);
    List<Car> result = carService.getAllCar();
    assertEquals(cars.size(), result.size());
}
    @Test
    @DisplayName("Car controller get all cars success")
    public void carService_deleteCar_success() {

        Long carId = 1L;
        Car car = new Car();
        car.setId(Math.toIntExact(carId));
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setColor("Red");
        car.setPrice(20000.0);

        when(carRepository.findById(Math.toIntExact(carId))).thenReturn(Optional.of(car));

        Car result = carService.getCarById(carId);

        assertEquals(car, result);

    }
    }

