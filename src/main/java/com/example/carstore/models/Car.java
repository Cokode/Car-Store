package com.example.carstore.models;

import jakarta.validation.constraints.NotEmpty;

public record Car(@NotEmpty Long id,
                  int year,
                  String name,
                  CarType carType,
                  Integer price,
                  String engineType,
                  String transmission,
                  String driveWheels,
                  double weight) {
}
