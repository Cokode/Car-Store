package com.example.carstore.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    long id;
    String names;
    String country;
    String address;
    int age;
    private long accountBalance = 374847383;
    private int carsOwned = 0;
    private List<Car> ListCarsBought = new ArrayList<>();

//    public Customer() {
//        this.id = 837383L;
//        this.names = "Collins Chinedu Amalimeh";
//        this.country = "Hungary";
//        this.address = "31/B Sostoi ut Nyiregyhaza";
//        this.age = 45;
//        this.accountBalance = 294839;
//        this.carsOwned = 0;
//        this.ListCarsBought = new ArrayList<>();
//    }

    public Customer(long id,
                    String names,
                    String country,
                    int age,
                    String address) {
        this.id = id;
        this.names = names;
        this.country = country;
        this.age = age;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getNames() {
        return names;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public int getCarsOwned() {
        return carsOwned;
    }

    public List<Car> getListCarsBought() {
        return ListCarsBought;
    }

    public void setAccountBalance(int amount) {
        if(amount > 0 ) this.accountBalance -= amount;
    }

    public void setCarsOwned(int num) {
        this.carsOwned += num;
    }

    public void setListCarsBought(Car car) {
        ListCarsBought.add(car);
    }
}
