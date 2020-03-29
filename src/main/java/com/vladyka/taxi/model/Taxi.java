package com.vladyka.taxi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taxi")
public class Taxi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "taxi_id")
    private int id;

    @Column(name = "model")
    @NotEmpty
    private String modelName;

    @Column(name = "cost_per_km")
    @NotEmpty
    private BigDecimal costPerKm;

    @Column(name = "free")
    private boolean isFree;

    @OneToMany(mappedBy="fkTaxi",cascade=CascadeType.ALL,fetch=FetchType.LAZY,
            orphanRemoval=true)
    private List<Order> orders;

}
