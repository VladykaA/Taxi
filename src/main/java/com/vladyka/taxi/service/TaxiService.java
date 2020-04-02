package com.vladyka.taxi.service;

import com.vladyka.taxi.model.Address;
import com.vladyka.taxi.model.Taxi;
import com.vladyka.taxi.repo.AddressRepository;
import com.vladyka.taxi.repo.TaxiRepository;
import org.springframework.stereotype.Service;

@Service
public class TaxiService {
    private static final int MIDDLE_SPEED = 50;

    private final AddressRepository addressRepository;

    private final TaxiRepository taxiRepository;

    public TaxiService(AddressRepository addressRepository, TaxiRepository taxiRepository) {
        this.addressRepository = addressRepository;
        this.taxiRepository = taxiRepository;
    }

    private int getDistanceFromLastPoint(Taxi taxi) {
        return addressRepository.findDistanceFromLastRide(taxi);
    }

    public Taxi getTaxiByModelName(String modelName) {
        return taxiRepository.findByModelName(modelName);
    }

    public int countTime(Address addressTo, Taxi taxi) {
        return (getDistanceFromLastPoint(taxi) + addressTo.getDistanceToCenter()) / MIDDLE_SPEED;
    }

}


