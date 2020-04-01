package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByName(String name);
}
