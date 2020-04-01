package com.vladyka.taxi.service;

import com.vladyka.taxi.model.Discount;
import com.vladyka.taxi.model.Order;
import com.vladyka.taxi.model.User;
import com.vladyka.taxi.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class OrderService {

    public static final int PERCENTS = 100;

    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order) {
     order.setPrice(calculatePrice(order));

        orderRepository.save(order);
    }

    private int calculateDiscount(User user) {
        int rides = orderRepository.countByUserId(user.getId());
        if (rides > Discount.PLATINUM.getValue()) {
            return 15;
        } else if (rides > Discount.GOLD.getValue()) {
            return 10;
        } else if (rides > Discount.SILVER.getValue()) {
            return 5;
        } else
            return 0;
    }

    private BigDecimal calculatePrice(Order order) {
        BigDecimal distance = BigDecimal.valueOf(order.getAddressFrom().getDistanceToCenter()
                + order.getAddressTo().getDistanceToCenter());

        BigDecimal rate = order.getTaxi().getCostPerKm();

        BigDecimal discount = BigDecimal.valueOf(calculateDiscount(order.getUser()));

        BigDecimal price = rate.multiply(distance);

        BigDecimal percentage = ((discount).divide(price)).multiply(BigDecimal.valueOf(PERCENTS));

        return price.subtract(percentage);

    }
}
