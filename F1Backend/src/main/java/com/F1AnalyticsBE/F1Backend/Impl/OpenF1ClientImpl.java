package com.F1AnalyticsBE.F1Backend.Impl;

import com.F1AnalyticsBE.F1Backend.Client.OpenF1Client;
import com.F1AnalyticsBE.F1Backend.model.OpenF1Driver;
import com.F1AnalyticsBE.F1Backend.model.OpenF1Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
@Service
@Component
@Slf4j
public class OpenF1ClientImpl implements OpenF1Client {

    @Value("${openf1.api.url:https://api.openf1.org/v1/}")
    private String openF1ApiUrl;
    private final RestTemplate restTemplate;

    public OpenF1ClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches the list of drivers from the OpenF1 API.
     *
     * @return List of OpenF1Driver objects or null if the request fails.
     */
    @Override
    public List<OpenF1Driver> getDrivers() {
        String url = UriComponentsBuilder.fromUriString(openF1ApiUrl)
                .path("drivers")
                .toUriString();
        log.info("Fetching drivers from OpenF1 API: {}", url);

        try{
            ResponseEntity<List<OpenF1Driver>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<OpenF1Driver>>() {}
            );

            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<OpenF1Driver> drivers = response.getBody();
                log.info("Successfully fetched {} drivers", drivers.size());
                return drivers;
            } else {
                log.error("Failed to fetch drivers, status code: {}", response.getStatusCode());
                return null;
            }
        }catch (Exception e) {
            log.error("Error fetching drivers from OpenF1 API: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<OpenF1Car> getCars() {
        String url = UriComponentsBuilder.fromUriString(openF1ApiUrl)
                .path("cars_data")
                .toUriString();
        log.info("Fetching cars from OpenF1 API: {}", url);

        try {
            ResponseEntity<List<OpenF1Car>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<OpenF1Car>>() {}
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<OpenF1Car> cars = response.getBody();
                log.info("Successfully fetched {} cars", cars.size());
                return cars;
            } else {
                log.error("Failed to fetch cars, status code: {}", response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("Error fetching cars from OpenF1 API: {}", e.getMessage(), e);
            return null;
        }
    }
}
