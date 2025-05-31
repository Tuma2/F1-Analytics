package com.F1AnalyticsBE.F1Backend.controller;


import com.F1AnalyticsBE.F1Backend.Service.DriverService;
import com.F1AnalyticsBE.F1Backend.model.Driver;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
@Slf4j
public class DriverController {

    @Autowired
    private final DriverService driverService;

    @GetMapping("/refresh")
    public ResponseEntity<List<Driver>> refreshDrivers() {
        log.info("Refreshing drivers from OpenF1 API");
        List<Driver> drivers = driverService.refreshDrivers();

        if (drivers != null && !drivers.isEmpty()) {
            log.info("Successfully refreshed {} drivers", drivers.size());
            return ResponseEntity.ok(drivers);
        } else {
            log.warn("No drivers found to refresh");
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        log.info("Fetching all drivers from the database");
        List<Driver> drivers = driverService.getAllDrivers();

        if (drivers != null && !drivers.isEmpty()) {
            log.info("Successfully fetched {} drivers", drivers.size());
            return ResponseEntity.ok(drivers);
        } else {
            log.warn("No drivers found in the database");
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Retrieves a single Driver entity by its driver number from the database.
     * @param driverNumber The unique driver number.
     * @return The Driver entity if found, or 404 Not Found.
     */
    @GetMapping("/{driverNumber}")
    public ResponseEntity<Driver> getDriverByNumber(@PathVariable int driverNumber) {
        log.info("Received request to get driver with number: {}", driverNumber);
        return driverService.getDriverByNumber(driverNumber)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK)) // If found, return 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // If not found, return 404 Not Found
    }
}
