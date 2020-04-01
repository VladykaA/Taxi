package com.vladyka.taxi.service;

import com.vladyka.taxi.model.Order;
import com.vladyka.taxi.model.User;
import com.vladyka.taxi.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order) {
        int discount = calculateDiscount(order.getUser());

//        order.setPrice();

        orderRepository.save(order);
    }

    private int calculateDiscount(User user) {
        return orderRepository.countByUserId(user.getId());
    }
}
