package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.Address;
import com.vladyka.taxi.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByName(String name);

    @Query("SELECT oad.distanceToCenter FROM Order o JOIN o.addressTo oad " +
            "WHERE o.taxi.modelName = :#{#taxi.modelName} ORDER BY o.id DESC")
    int findDistanceFromLastRide(@Param("taxi") Taxi taxi);
}
