package com.F1AnalyticsBE.F1Backend.Impl;

import com.F1AnalyticsBE.F1Backend.Repository.CarRepository;
import com.F1AnalyticsBE.F1Backend.Service.CarService;
import com.F1AnalyticsBE.F1Backend.model.Car;
import com.F1AnalyticsBE.F1Backend.model.OpenF1Car;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;

    private OpenF1ClientImpl openF1Client;


    public List<Car> refreshCars() {
        List<OpenF1Car> openF1Cars = openF1Client.getCars();
        if (openF1Cars != null && !openF1Cars.isEmpty()) {
            return openF1Cars.stream()
                    .map(this::mapToCar)
                    .map(carRepository::save)
                    .toList();
        } else {
            return null; // No cars found in OpenF1Client
        }
    }

    public List<Car> getDriverCar(int driverNumber, int sessionKey, int speed) {
        List<Car> cars = carRepository.findByDriverNumberAndSessionKey(driverNumber,sessionKey);
        if (cars != null && !cars.isEmpty()) {
            log.info("found {} cars for driver number {} and session key {}", cars.size(), driverNumber, sessionKey);
            return cars;
        } else {
            log.warn("No cars found for driver number {} and session key {}", driverNumber, sessionKey);
            return null; // No cars found for the given driver number and session key
        }
    }

    /**
     * Maps an OpenF1Car object to a Car object.
     *
     * @param openF1Car the OpenF1Car object to map
     * @return the mapped Car object
     */
    private Car mapToCar(OpenF1Car openF1Car) {
        Car car = new Car();
        car.setDriverNumber(openF1Car.getDriverNumber());
        car.setSessionKey(openF1Car.getSessionKey());
        car.setBrake(openF1Car.getBrake());
        car.setThrottle(openF1Car.getThrottle());
        car.setSpeed(openF1Car.getSpeed());
        car.setDrs(openF1Car.getDrs());
        car.setRpm(openF1Car.getRpm());
        car.setNGear(openF1Car.getNGear());
        car.setDate(openF1Car.getDate());
        car.setMeetingKey(openF1Car.getMeetingKey());

        return car;
    }
}
