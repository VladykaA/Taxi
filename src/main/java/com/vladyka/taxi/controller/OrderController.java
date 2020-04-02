package com.vladyka.taxi.controller;

import com.vladyka.taxi.dto.OrderDTO;
import com.vladyka.taxi.model.Order;
import com.vladyka.taxi.service.OrderService;
import com.vladyka.taxi.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    private final OrderService orderService;

    private final TaxiService taxiService;

    public OrderController(OrderService orderService, TaxiService taxiService) {
        this.orderService = orderService;
        this.taxiService = taxiService;
    }

    @PostMapping(value = "/save")
    public ModelAndView save(@RequestBody OrderDTO orderDTO) {
        ModelAndView modelAndView = new ModelAndView("order");
        Order order = orderService.save(orderDTO);

        int countTime = taxiService.countTime(order.getAddressTo(), order.getTaxi());

        modelAndView.addObject("CountTime", countTime);

        return modelAndView;
    }


}
