package com.F1AnalyticsBE.F1Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id // Marks 'driverNumber' as the primary key
    @Column(name = "driver_number", unique = true, nullable = false)
    private int driverNumber;
    private String broadcastName;
    private String countryCode;
//    private LocalDate dateOfBirth;
    private String nameAbbreviation;
    private String name; // Typically stores the first name
    private String surname;
    private String driverImageUrl;

    // NOTE: sessionId and meetingId are included here based on your previous model.
    // In a more normalized database design, these might be part of separate
    // entities (e.g., Session, Meeting) with relationships established.
    // For now, they are kept as String fields for direct mapping.

    private String sessionId;
    private String meetingId;
    private String teamName;
    private String teamColor;
}
