package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(int id);

    List<Order> findAll();

    /*@Query("SELECT o FROM Order o WHERE o.taxi.modelName = :#{#taxi.modelName} ORDER BY o.id DESC")
    Optional<Order> findTopByOrderByTaxi(@Param("taxi") Taxi taxi);*/

    int countByUserId(int id);


}
