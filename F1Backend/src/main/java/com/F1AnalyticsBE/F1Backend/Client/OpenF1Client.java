package com.F1AnalyticsBE.F1Backend.Client;

import com.F1AnalyticsBE.F1Backend.model.OpenF1Driver;
import com.F1AnalyticsBE.F1Backend.model.OpenF1Car;

import java.util.List;

public interface OpenF1Client {

    List<OpenF1Driver> getDrivers();

    List<OpenF1Car> getCars();


}
