package com.suber.service;

import com.suber.repository.CompanyRepository;
import com.suber.repository.OrderRepository;
import com.suber.services.CompanyService;
import com.suber.services.OrderService;
import com.suber.util.TestData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService service;
    @MockBean
    OrderRepository repository;

    @Test
    void testFindOrderUsingReferenceWithMockedDatabaseRepository() {
        Mockito.when(repository.findByReference("1234-1")).thenReturn(List.of(TestData.getOrder()));
        Assert.assertEquals(1, service.findByReference("1234-1").size());
    }

    @Test
    void testFindOrderUsingIdWithMockedDatabaseRepository() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(TestData.getOrder()));
        Assert.assertEquals("23211", service.findById(1).get().getReference());
    }

    @Test
    void testFindAllOrdersWithMockedDatabaseRepository() {
        Mockito.when(repository.findAll()).thenReturn(List.of(TestData.getOrder(), TestData.getOrder()));
        Assert.assertEquals(2, service.findAll().size());
    }


}
