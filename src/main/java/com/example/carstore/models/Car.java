package com.example.carstore.models;

public record Car(Long id,
                  int year,
                  String name,
                  CarType carType,
                  Integer price,
                  String engineType,
                  String transmission,
                  String driveWheels,
                  double weight) {
}
