package com.vladyka.taxi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int id;

    @NotEmpty
    private String status;

    @Column(name = "trip_date_time")
    @NotEmpty
    private LocalDateTime tripDate;

    @Column(name = "trip_time")
    @NotEmpty
    private Duration tripTime;

    private int discount;

    @NotEmpty
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "address_from_fk")
    private Address addressFrom;

    @OneToOne
    @JoinColumn(name = "address_to_fk")
    private Address addressTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_taxi")
    private Taxi taxi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;

}

/*
* 1) Расчет скидки(подсчитать сколько раз данный юзер заказывал такси) и изменение цены заказа,
* скидка хранится в Энаме
* 2) Расчет времени ожидания такси(получить из БД последний заказ данного такси(modelName or id) и
* потом получить расстояние до центра и потом сложить с расстоянием от центра до адреса ,к-рый выбрал юзер)
* 3) Сохранение заказа с измененной ценой
* */
