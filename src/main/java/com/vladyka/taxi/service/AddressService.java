package com.vladyka.taxi.service;

import com.vladyka.taxi.model.Address;
import com.vladyka.taxi.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address getAddressByName(String name) {
        return addressRepository.findByName(name);
    }
}
