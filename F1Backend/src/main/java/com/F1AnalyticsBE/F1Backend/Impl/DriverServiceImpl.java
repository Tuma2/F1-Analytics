package com.F1AnalyticsBE.F1Backend.Impl;

import com.F1AnalyticsBE.F1Backend.Repository.DriverRepository;
import com.F1AnalyticsBE.F1Backend.Service.DriverService;
import com.F1AnalyticsBE.F1Backend.model.Driver;
import com.F1AnalyticsBE.F1Backend.model.OpenF1Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DriverServiceImpl.class);

    @Autowired
    private final DriverRepository driverRepository;

    private final OpenF1ClientImpl openF1Client;

    @Override
    public List<Driver> refreshDrivers() {
        List<OpenF1Driver> openF1Drivers = openF1Client.getDrivers();
        if (openF1Drivers == null || openF1Drivers.isEmpty()) {
            return List.of();
        }
        driverRepository.deleteAll();

        // Filter for active drivers (teamName != null), then deduplicate
        List<Driver> drivers = new ArrayList<>(openF1Drivers.stream()
                .filter(d -> d.getTeamName() != null)
                .filter(d -> d.getHeadshotUrl() != null && !d.getHeadshotUrl().isBlank())
                .map(this::mapToDriver)
                .collect(Collectors.toMap(
                        Driver::getDriverNumber,
                        d -> d,
                        (existing, replacement) -> existing
                ))
                .values());

        return driverRepository.saveAll(drivers);
    }

    public List<Driver> getAllDrivers() {
        log.info("Fetching all drivers from the database.");
        return driverRepository.findAll();
    }

//    @Override
//    public Driver getDriverByNumber(String driverNumber) {
//        try {
//            int number = Integer.parseInt(driverNumber);
//            return getDriverByNumber(number).orElse(null);
//        } catch (NumberFormatException e) {
//            log.error("Invalid driver number format: {}", driverNumber);
//            return null;
//        }
//    }

    public Optional<Driver> getDriverByNumber(int driverNumber) {
        log.info("Fetching driver with number {} from the database.", driverNumber);
        return driverRepository.findById(driverNumber);
    }

    private Driver mapToDriver(OpenF1Driver openF1Driver) {
        Driver driver = new Driver();
        driver.setDriverNumber(openF1Driver.getDriverNumber());
        driver.setBroadcastName(openF1Driver.getBroadcastName());
        driver.setCountryCode(openF1Driver.getCountryCode());
//        driver.setDateOfBirth(openF1Driver.g());
        driver.setName(openF1Driver.getFirstName());
        driver.setSurname(openF1Driver.getLastName());
        driver.setDriverImageUrl(openF1Driver.getHeadshotUrl());
        driver.setSessionId(String.valueOf(openF1Driver.getSessionKey())); // Set as needed
        driver.setMeetingId(String.valueOf(openF1Driver.getMeetingKey())); // Set as needed
        driver.setTeamName(openF1Driver.getTeamName());
        driver.setTeamColor(openF1Driver.getTeamColor());
        driver.setNameAbbreviation(openF1Driver.getNameAcronym()); // Set as needed
        return driver;
    }
}
