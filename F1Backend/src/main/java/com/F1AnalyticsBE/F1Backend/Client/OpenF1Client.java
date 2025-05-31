package com.F1AnalyticsBE.F1Backend.Client;

import com.F1AnalyticsBE.F1Backend.model.OpenF1Driver;

import java.util.List;

public interface OpenF1Client {

    List<OpenF1Driver> getDrivers();
}
