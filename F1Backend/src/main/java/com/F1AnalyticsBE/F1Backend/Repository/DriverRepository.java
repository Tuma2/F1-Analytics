package com.F1AnalyticsBE.F1Backend.Repository;

import com.F1AnalyticsBE.F1Backend.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    // Additional query methods can be defined here if needed
}
