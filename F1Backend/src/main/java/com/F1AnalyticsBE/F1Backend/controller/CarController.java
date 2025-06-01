package com.F1AnalyticsBE.F1Backend.controller;

import com.F1AnalyticsBE.F1Backend.Service.CarService;
import com.F1AnalyticsBE.F1Backend.model.Car;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/refresh")
    public ResponseEntity<List<Car>> refreshCars() {
        log.info("Refreshing cars from OpenF1 API");
        List<Car> cars = carService.refreshCars();

        if (cars != null && !cars.isEmpty()) {
            log.info("Successfully refreshed {} cars", cars.size());
            return ResponseEntity.ok(cars);
        } else {
            log.warn("No cars found to refresh");
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{driverNumber}/{sessionKey}/speed/{speed}")
    public ResponseEntity<List<Car>> getDriverCar(@PathVariable int driverNumber,@PathVariable int sessionKey, @PathVariable int speed) {
        log.info("Fetching cars for driver number {} and session key {}", driverNumber, sessionKey);
        List<Car> cars = carService.getDriverCar(driverNumber, sessionKey,speed);

        if (cars != null && !cars.isEmpty()) {
            log.info("Found {} cars for driver number {} and session key {}", cars.size(), driverNumber, sessionKey);
            return ResponseEntity.ok(cars);
        } else {
            log.warn("No cars found for driver number {} and session key {}", driverNumber, sessionKey);
            return ResponseEntity.noContent().build();
        }
    }
}
