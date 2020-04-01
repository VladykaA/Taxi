package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(int id);

    List<Order> findAll();

    int countByUserId(int id);
}
