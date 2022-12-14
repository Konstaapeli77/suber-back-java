package com.suber.controller;

import com.suber.data.Company;
import com.suber.data.Order;
import com.suber.data.Person;
import com.suber.dto.*;
import com.suber.repository.CompanyRepository;
import com.suber.repository.OrderRepository;
import com.suber.util.TestData;
import com.suber.util.mapper.DataMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @Autowired
    OrderController controller;

    @Autowired
    OrderRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void orderShouldBeDeletedAndReturnedSuccess() {


        Order savedorder = repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));

        ResponseEntity<HttpStatus> status = controller.deleteOrder(savedorder.getId());

        Optional<Order> found = repository.findById(savedorder.getId());

        Assert.assertFalse(found.isPresent());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void allOrdersShouldBeDeletedAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));
        repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));
        List<Order> found = repository.findAll();
        System.out.println("size: " + found.size());

        ResponseEntity<HttpStatus> status = controller.deleteAllOrders();

        List<Order> foundDeleted = repository.findAll();
        System.out.println("size: " + foundDeleted.size());

        Assert.assertTrue(found.size() > 0);
        Assert.assertEquals(0, foundDeleted.size());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void orderShouldBeFoundWithFirstnameAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));

        ResponseEntity<OrderListDTO> personsWithThatName = controller.findByReference(TestData.getOrderDTO().getReference());
        OrderListDTO list = personsWithThatName.getBody();

        Assert.assertTrue(list.getOrders().size() > 0);
        Assert.assertTrue(list.getOrders().get(0).getReference().equals(TestData.getOrderDTO().getReference()));
        Assert.assertEquals(HttpStatus.OK, personsWithThatName.getStatusCode());
    }

    @Test
    void orderShouldNotBeFoundWithFirstnameAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));

        ResponseEntity<OrderListDTO> orderWithThatReference = controller.findByReference("Jaakkokaakko");
        OrderListDTO list = orderWithThatReference.getBody();

        Assert.assertTrue(list == null);
        Assert.assertEquals(HttpStatus.NO_CONTENT, orderWithThatReference.getStatusCode());
    }

    @Test
    void orderWithAddressShouldBeUpdatedAndReturnedSuccess() {

        OrderDTO dto = TestData.getOrderDTO();
        dto.setAddress(TestData.getAddressDTO());

        Order savedOrder = repository.save(DataMapper.getInstance().convertToEntity(dto));
        savedOrder.setReference("Väliaho");

        OrderDTO orderDTOFromPerson = DataMapper.getInstance().convertToDto(savedOrder);
        ResponseEntity<OrderDTO> updatedOrder =
                controller.updateOrder(savedOrder.getId(), orderDTOFromPerson);

        Assert.assertEquals(HttpStatus.OK, updatedOrder.getStatusCode());
        Assert.assertTrue(updatedOrder.getBody().getReference().equals("Väliaho"));
    }

    @Test
    void orderShouldBeUpdatedAndReturnedSuccess() {

        Order savedOrder = repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));
        savedOrder.setReference("Väliaho");

        OrderDTO orderDTOFromOrder = DataMapper.getInstance().convertToDto(savedOrder);
        ResponseEntity<OrderDTO> updatedOrder =
                controller.updateOrder(savedOrder.getId(), orderDTOFromOrder);

        Assert.assertEquals(HttpStatus.OK, updatedOrder.getStatusCode());
        Assert.assertTrue(updatedOrder.getBody().getReference().equals("Väliaho"));
    }

    @Test
    void orderShouldNotBeUpdatedAndReturnFailed() {

        long wrongId = 10000;

        Order savedOrder = repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));

        OrderDTO orderDTOFromPerson = DataMapper.getInstance().convertToDto(savedOrder);
        ResponseEntity<OrderDTO> updatedPerson =
                controller.updateOrder(wrongId, orderDTOFromPerson);

        Assert.assertEquals(HttpStatus.NOT_FOUND, updatedPerson.getStatusCode());
        Assert.assertTrue(updatedPerson.getBody() == null);
    }

    @Test
    void getAllOrdersAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));
        repository.save(DataMapper.getInstance().convertToEntity(TestData.getOrderDTO()));
        List<Order> found = repository.findAll();
        System.out.println("size: " + found.size());

        ResponseEntity<OrderListDTO> listOfPersons = controller.getAllOrders();
        System.out.println("size: " + listOfPersons.getBody().getOrders().size());

        Assert.assertEquals(HttpStatus.OK, listOfPersons.getStatusCode());
        Assert.assertTrue(listOfPersons.getBody().getOrders().size() > 0);

    }

}
