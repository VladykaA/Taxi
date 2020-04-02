package com.vladyka.taxi.service;

import com.vladyka.taxi.dto.OrderDTO;
import com.vladyka.taxi.model.*;
import com.vladyka.taxi.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private final OrderRepository orderRepository;

    private final TaxiService taxiService;

    private final AddressService addressService;

    public OrderService(OrderRepository orderRepository, TaxiService taxiService,
                        AddressService addressService) {
        this.orderRepository = orderRepository;
        this.taxiService = taxiService;
        this.addressService = addressService;
    }

    public Order save(OrderDTO orderDTO) {

        User user = User
                .builder()
                .id((int)orderDTO.getUserId())
                .build();

        int discount = calculateDiscount(user);

        Taxi taxi = taxiService.getTaxiByModelName(orderDTO.getModelName());

        Address addressTo = addressService.getAddressByName(orderDTO.getAddressTo());

        Address addressFrom = addressService.getAddressByName(orderDTO.getAddressFrom());

        Order order = Order
                .builder()
                .addressFrom(addressFrom)
                .addressTo(addressTo)
                .taxi(taxi)
                .status("NEW")
                .user(user)
                .tripDate(LocalDateTime.now())
                .discount(discount)
                .build();

        order.setPrice(calculatePrice(order));

        orderRepository.save(order);

        return order;
    }

    private int calculateDiscount(User user) {
        int rides = orderRepository.countByUserId(user.getId());

        if (rides > Discount.PLATINUM.getValue()) {
            return Discount.PLATINUM.getValue();
        } else if (rides > Discount.GOLD.getValue()) {
            return Discount.GOLD.getValue();
        } else if (rides > Discount.SILVER.getValue()) {
            return Discount.SILVER.getValue();
        } else {
            return Discount.ZERO.getValue();
        }

        /*return Arrays
                .stream(Discount.values())
                .filter(d -> rides > d.getValue())
                .findFirst()
                .orElse(Discount.ZERO).getValue();
        */
    }

    private BigDecimal calculatePrice(Order order) {
        int d = order.getAddressFrom().getDistanceToCenter() +
                order.getAddressTo().getDistanceToCenter();

        BigDecimal distance = BigDecimal.valueOf(d);

        BigDecimal rate = order.getTaxi().getCostPerKm();

        int calcDiscount = calculateDiscount(order.getUser());

        BigDecimal discount = BigDecimal.valueOf(calcDiscount);

        BigDecimal price = rate.multiply(distance);

        BigDecimal percentage = discount.multiply(price).divide(ONE_HUNDRED, RoundingMode.UP);

        return price.subtract(percentage).setScale(2, RoundingMode.UP);

    }
}


