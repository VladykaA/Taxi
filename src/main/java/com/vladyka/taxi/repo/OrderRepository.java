package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(Order order);
}
