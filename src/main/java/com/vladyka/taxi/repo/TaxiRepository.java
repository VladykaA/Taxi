package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaxiRepository extends JpaRepository<Taxi, Long> {
    List<Taxi> findAll();
}
