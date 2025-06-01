package com.F1AnalyticsBE.F1Backend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenF1Car {

    @JsonProperty("brake")
    private String brake;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("driver_number")
    private int driverNumber;

    @JsonProperty("drs")
    private int drs;

    @JsonProperty("meeting_key")
    private Integer meetingKey;

    @JsonProperty("n_gear")
    private int nGear;

    @JsonProperty("rpm")
    private int rpm;

    @JsonProperty("session_key")
    private Integer sessionKey;

    @JsonProperty("speed")
    private int speed;

    @JsonProperty("throttle")
    private int throttle;


}
