package com.suber.controller;

import com.suber.dto.OrderDTO;
import com.suber.dto.OrderListDTO;
import com.suber.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<OrderListDTO> getAllOrders(@RequestParam(required = false) String reference) {
        try {
            List<OrderDTO> orders = new ArrayList<OrderDTO>();

            if (reference == null)
                orderService.findAll().forEach(orders::add);
            else
                orderService.findByReference(reference).forEach(orders::add);

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //return new ResponseEntity<>(orders, HttpStatus.OK);
            return new ResponseEntity<>(new OrderListDTO(orders), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") long id) {
        Optional<OrderDTO> orderData = orderService.findById(id);

        if (orderData.isPresent()) {
            return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createCompany(@RequestBody OrderDTO order) {
        try {
            OrderDTO _order = orderService
                    .save(order);
            return new ResponseEntity<>(_order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> updateCompany(@PathVariable("id") long id, @RequestBody OrderDTO order) {
        Optional<OrderDTO> orderData = orderService.findById(id);

        if (orderData.isPresent()) {
            return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") long id) {
        try {
            orderService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/orders")
    public ResponseEntity<HttpStatus> deleteAllOrders() {
        try {
            orderService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/orders/name")
    public ResponseEntity<OrderListDTO> findByName(@PathVariable("reference") String reference) {
        try {
            List<OrderDTO> orders = orderService.findByReference(reference);

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new OrderListDTO(orders), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
