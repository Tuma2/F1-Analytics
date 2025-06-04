package com.F1AnalyticsBE.F1Backend.Impl;

import com.F1AnalyticsBE.F1Backend.Client.OpenF1Client;
import com.F1AnalyticsBE.F1Backend.model.OpenF1Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
@Component
@Slf4j
public class OpenF1ClientImpl implements OpenF1Client {

    @Value("${openf1.api.url:https://api.openf1.org/v1/}")
    private String openF1ApiUrl;
    private final RestTemplate restTemplate;

    public OpenF1ClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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

            if (response.getStatusCode().is2xxSuccessful()) {
                List<OpenF1Driver> drivers = response.getBody();
                if (drivers != null) {
                    log.info("Successfully fetched {} drivers", drivers.size());
                    return drivers;
                } else {
                    log.warn("No drivers found (empty response body)");
                    return List.of();
                }
            } else {
                log.error("Failed to fetch drivers, status code: {}", response.getStatusCode());
                return List.of();
            }
        }catch (Exception e) {
            log.error("Error fetching drivers from OpenF1 API: {}", e.getMessage(), e);
            return null;
        }
    }
}
