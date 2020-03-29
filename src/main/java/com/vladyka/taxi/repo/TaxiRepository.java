package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public interface TaxiRepository extends JpaRepository<Taxi, Long> {

    Taxi findByModelNameAndFreeAndCostPerKm(@NotEmpty String modelName, boolean free, @NotEmpty BigDecimal costPerKm);
}
