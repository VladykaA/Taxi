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

    @Transient
    private Address addressFrom;
    @Transient
    private Address addressTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_taxi")
    private Taxi fkTaxi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User fkUser;

}
