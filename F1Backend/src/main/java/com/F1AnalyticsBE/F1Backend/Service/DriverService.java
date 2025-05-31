package com.F1AnalyticsBE.F1Backend.Service;

import com.F1AnalyticsBE.F1Backend.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    public List<Driver> refreshDrivers();
    public List<Driver> getAllDrivers();
//    public Driver getDriverByNumber(String driverNumber);

    Optional<Driver> getDriverByNumber(int driverNumber);
}
