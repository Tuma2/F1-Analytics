package com.F1AnalyticsBE.F1Backend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenF1Driver {

    @JsonProperty("meeting_key")
    private Integer meetingKey;

    /**
     * The unique key identifying the session (e.g., Free Practice 1, Qualifying, Race). Maps from 'session_key' in API.
     */
    @JsonProperty("session_key")
    private Integer sessionKey;

    /**
     * The unique identifying number for the driver. Maps from 'driver_number' in API.
     */
    @JsonProperty("driver_number")
    private int driverNumber;

    /**
     * The broadcast name of the driver (e.g., "L HAMILTON"). Maps from 'broadcast_name' in API.
     */
    @JsonProperty("broadcast_name")
    private String broadcastName;

    /**
     * The full name of the driver (e.g., "Lewis HAMILTON"). Maps from 'full_name' in API.
     */
    @JsonProperty("full_name")
    private String fullName;

    /**
     * The name acronym/abbreviation of the driver (e.g., "HAM"). Maps from 'name_acronym' in API.
     */
    @JsonProperty("name_acronym")
    private String nameAcronym;

    /**
     * The name of the team the driver belongs to (e.g., "Mercedes"). Maps from 'team_name' in API.
     */
    @JsonProperty("team_name")
    private String teamName;

    /**
     * The primary color associated with the driver's team (e.g., "6CD3BF"). Maps from 'team_colour' in API.
     */
    @JsonProperty("team_colour") // Note the British spelling 'colour' from the API
    private String teamColor; // Java field name uses American spelling for consistency

    /**
     * The first name of the driver (e.g., "Lewis"). Maps from 'first_name' in API.
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * The last name (surname) of the driver (e.g., "Hamilton"). Maps from 'last_name' in API.
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * URL to the driver's headshot image. Maps from 'headshot_url' in API.
     */
    @JsonProperty("headshot_url")
    private String headshotUrl;

    /**
     * The country code of the driver (e.g., "GBR"). Maps from 'country_code' in API.
     */
    @JsonProperty("country_code")
    private String countryCode;

}
