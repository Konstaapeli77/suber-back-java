package com.suber.services;

import com.suber.dto.OrderDTO;
import com.suber.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDTO save(OrderDTO orderDTO);

    Optional<OrderDTO> findById(long id);

    List<OrderDTO> findAll();

    List<OrderDTO> findByReference(String reference);

    void deleteById(long id);

    void deleteAll();
}
