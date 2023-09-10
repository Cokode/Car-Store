package com.example.carstore.Repository;

import com.example.carstore.models.Car;
import com.example.carstore.models.CarBuyer;
import com.example.carstore.models.Customer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {

    final List<Car> cars = new ArrayList<>(5);
    final List<CarBuyer> carBuyers = new ArrayList<>();
    final List<Customer> listCustomers = new ArrayList<>();

    public Optional<Car> findByID(Long id) {
        return cars.stream().filter(car -> car.id().equals(id)).findFirst();
    }

    // check if cars are available
    public boolean carsAvailable() {
        return cars.size() >= 1;
    }

    public List<CarBuyer> getCarBuyers () {
        return carBuyers;
    }

    // get / diplay all cars
    public List<Car> getAllCar() {
        return cars;
    }

    // remove cars sold from inventory
    public void removeCarBought(Car car, CarBuyer carBuyer) {
        cars.remove(car);
        carBuyers.add(carBuyer);
    }

    //add car to showroom / list
    public void save (Car car) {
        cars.add(car);
    }

    //find car and return value
    public Car findCar(Long id) {
        for (Car c : cars) {
            if (c.id().equals(id)) return c;
        }
        return null;
    }

    //check customer balance
    public boolean checkCustomerBalance(Customer customer, Integer price) {
        return (customer.getAccountBalance()) - price >= 0;
    }

    public List<Customer> getListCustomers () {
        return listCustomers;
    }

    //buyer functionality
    public boolean buyCar (Long id, Customer customer) {
        Car c = findCar(id);

        if (carsAvailable() && c != null && checkCustomerBalance(customer, c.price())) {
            customer.setAccountBalance(c.price());
            String localDate = String.valueOf(LocalDateTime.now());
            removeCarBought(c, new CarBuyer(customer.getNames(), c, 1, localDate));
            customer.setCarsOwned(1);
            customer.setListCarsBought(c);
            listCustomers.add(customer);
            return true;
        }

        return false;
    }
}
