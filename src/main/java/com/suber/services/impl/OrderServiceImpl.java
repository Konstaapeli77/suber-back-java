package com.suber.services.impl;

import com.suber.data.Order;
import com.suber.data.Person;
import com.suber.dto.OrderDTO;
import com.suber.dto.PersonDTO;
import com.suber.exception.ResourceNotFoundException;
import com.suber.repository.OrderRepository;
import com.suber.services.OrderService;
import com.suber.util.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = DataMapper.getInstance().convertToEntity(orderDTO);
        orderRepository.save(order);
        return orderDTO;
    }

    @Override
    public Optional<OrderDTO> findById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        OrderDTO originalOrderDTO = new OrderDTO();
        if (order.isPresent()) {
            originalOrderDTO = DataMapper.getInstance().convertToDto(order.get());
        } else {
            return Optional.empty();
        }
        Optional<OrderDTO> orderDTO= Optional.of(originalOrderDTO);
        return orderDTO;
    }

    @Override
    public Optional<OrderDTO> updateOrder(long id, OrderDTO updatedOrder) throws ResourceNotFoundException {
        Optional<Order> order = orderRepository.findById(id);

        OrderDTO result = null;
        if (order.isPresent()) {
            Order orderEntity = order.get();
            orderEntity.setPrice(updatedOrder.getPrice());
            orderEntity.setReference(updatedOrder.getReference());
            if (updatedOrder.getAddress() == null) {
                orderEntity.setAddress(null);
            } else {
                orderEntity.setAddress(DataMapper.getInstance().convertToEntity(updatedOrder.getAddress()));
            }
            Order updatedOrderEntity = orderRepository.save(orderEntity);
            result = DataMapper.getInstance().convertToDto(updatedOrderEntity);
        } else {
            return Optional.empty();
        }

        Optional<OrderDTO> orderDTO= Optional.of(result);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        List<Order> orders = new ArrayList<Order>();
        orderRepository.findAll().forEach(orders::add);
        for (Order order:orders) {
            OrderDTO orderDTO = DataMapper.getInstance().convertToDto(order);
            ordersDTO.add(orderDTO);
        }
        return ordersDTO;
    }

    @Override
    public List<OrderDTO> findByReference(String reference) {
        List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
        List<Order> orders = new ArrayList<Order>();
        orderRepository.findByReference(reference).forEach(orders::add);
        for (Order order:orders) {
            OrderDTO orderDTO = DataMapper.getInstance().convertToDto(order);
            ordersDTO.add(orderDTO);
        }
        return ordersDTO;
    }

    @Override
    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }
}
