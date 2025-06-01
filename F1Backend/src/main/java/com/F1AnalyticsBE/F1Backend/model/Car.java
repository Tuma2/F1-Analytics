package com.F1AnalyticsBE.F1Backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private int driverNumber;
    private String brake;
    private LocalDate date; // Assuming date is stored as a String in the format "YYYY-MM-DD"
    private int drs;
    private Integer meetingKey;
    private int nGear;
    private int rpm;
    private Integer sessionKey;
    private int speed;
    private int throttle;
}

