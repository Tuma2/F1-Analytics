package com.F1AnalyticsBE.F1Backend.Impl;

import com.F1AnalyticsBE.F1Backend.Repository.DriverRepository;
import com.F1AnalyticsBE.F1Backend.Service.DriverService;
import com.F1AnalyticsBE.F1Backend.model.Driver;
import com.F1AnalyticsBE.F1Backend.model.OpenF1Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DriverServiceImpl.class);

    @Autowired
    private final DriverRepository driverRepository;

    private final OpenF1ClientImpl openF1Client;

    public List<Driver> refreshDrivers() {
        log.info("Refreshing drivers from OpenF1 API");
        List<OpenF1Driver> openF1drivers = openF1Client.getDrivers();

        if (openF1drivers != null && !openF1drivers.isEmpty()) {
            log.info("Saving {} drivers to the database", openF1drivers.size());
            return openF1drivers.stream()
                    .map(this::mapToDriver)
                    .map(driverRepository::save)
                    .toList();
        } else {
            log.warn("No drivers found to save");
            return null;
        }
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
