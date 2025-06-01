package com.F1AnalyticsBE.F1Backend.Service;

import com.F1AnalyticsBE.F1Backend.model.Car;

import java.util.List;

public interface CarService {

    public List<Car> refreshCars();

    public List<Car> getDriverCar(int driverNumber, int sessionKey, int speed);
}
