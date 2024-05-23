package com.binary.CarShow.services;

import com.binary.CarShow.entities.Car;
import com.binary.CarShow.entities.Owner;
import com.binary.CarShow.repositatories.CarRepository;
import com.binary.CarShow.repositatories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Car createCar(Car car) {

        if (car == null) {
            return null;
        }
        if (car.getOwner() != null && car.getOwner().getOwnerId() == null) {
            ownerRepository.save(car.getOwner());
        }
        Car savedCar = carRepository.save(car);
        return carRepository.save(savedCar);
    }

    @Override
    public List<Car> getAllCar() {
        return (List<Car>) carRepository.findAll();


    }

    @Override
    public Car updateCar(long id, Car updatedCar) {
        Optional<Car> optionalCar = carRepository.findById((int) id);
        if (optionalCar.isPresent()) {
            if (updatedCar.getBrand() != null) {
                optionalCar.get().setBrand(updatedCar.getBrand());
            }
            if (updatedCar.getModel() != null) {
                optionalCar.get().setModel(updatedCar.getModel());
            }
            if (updatedCar.getColor() != null) {
                optionalCar.get().setColor(updatedCar.getColor());
            }
            if (updatedCar.getPrice() > 0) {
                optionalCar.get().setPrice(updatedCar.getPrice());
            }
            if (updatedCar.getYear() != null) {
                optionalCar.get().setYear(updatedCar.getYear());
            }
            if (updatedCar.getOwner() != null) {
                optionalCar.get().setOwner(updatedCar.getOwner());
            }
            carRepository.save(optionalCar.get());
            return optionalCar.get();
        }
        return null;
    }
    @Override
    public Long deleteCar(Long id) {
        Optional<Car> car = carRepository.findById(Math.toIntExact(id));
        if(car.isPresent()){
            carRepository.deleteById(Math.toIntExact(id));
            return id;
        }

        return null;
    }

    @Override
    public Car getCarById(Long id) {
        Optional<Car> car = carRepository.findById(Math.toIntExact(id));
        if (car.isPresent()) {
            return car.get();
        } else {
            return null;
            //throw new IllegalArgumentException(id + " Car Does not exist.");
        }
    }
}
