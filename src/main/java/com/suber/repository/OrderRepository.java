package com.suber.repository;

import com.suber.data.Company;
import com.suber.data.Order;
import com.suber.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(@Param("id") long id);
    List<Order> findByReference(@Param("reference") String reference);

}
