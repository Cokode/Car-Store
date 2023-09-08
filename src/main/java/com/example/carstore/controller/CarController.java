package com.example.carstore.controller;

import com.example.carstore.Repository.CarRepository;
import com.example.carstore.models.Car;
import com.example.carstore.models.CarBuyer;
import com.example.carstore.models.CarType;
import com.example.carstore.models.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car/home")
public class CarController {
    CarRepository carRepository;

    @Autowired
    public CarController (CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping()
    public List<Car> getAllCars () {
        if(carRepository.carsAvailable()) {
            return carRepository.getAllCar();
        }
       return new ArrayList<>();
    }

    @GetMapping("/id")
    public Optional<Car> findCarById (@RequestParam Integer id) {
       return Optional.ofNullable(carRepository.findByID(Long.valueOf(id)).
               orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found")));
    }

    @GetMapping("/carbuyers")
    public List<CarBuyer> getCarBuyer() {
        return carRepository.getCarBuyers();
    }

    @GetMapping("/getCustomerInfo")
    public List<Customer> getListCustomer() {
        return carRepository.getListCustomers();
    }

    @PostMapping("/buycar")
    public String buyCar (@RequestBody Customer customer, @RequestParam Long id) {
        if(carRepository.buyCar(id, customer)) {
            return "Successfully bought a car";
        }
        return "encountered a problem";
    }


    @PostConstruct
    public void cars() {
        Car bmw = new Car(263728L,
                2023,
                "BMW",
                CarType.AUTO,
                36000,
                "In-line 4-cylinder engine with Lexus Hybrid Drive",
                "Electronically controlled Continuously Variable Transmission (ECVT)",
                "Front-wheel drive (FWD)",
                3483.50
        );
        Car lexus = new Car(453028L,
                2022,
                "Lexus",
                CarType.AUTO,
                63600,
                "In-line 4-cylinder engine with Lexus Hybrid Drive",
                "Electronically controlled Continuously Variable Transmission (ECVT)",
                "All-wheel drive* (AWD)",
                5483.50
        );
        Car mercedes = new Car(263728L,
                2020,
                "mercedes Benz",
                CarType.AUTO,
                52800,
                "In-line 4-cylinder engine with Lexus Hybrid Drive",
                "Electronically controlled Continuously Variable Transmission (ECVT)",
                "All-wheel drive* (AWD)",
                6783.50
        );

        carRepository.save(bmw);
        carRepository.save(lexus);
        carRepository.save(mercedes);
    }
}
