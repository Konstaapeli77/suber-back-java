package com.suber.service;

import com.suber.repository.OrderRepository;
import com.suber.repository.ServiceRepository;
import com.suber.services.OrderService;
import com.suber.services.ServiceService;
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
public class ServiceServiceTest {

    @Autowired
    ServiceService service;
    @MockBean
    ServiceRepository repository;

    @Test
    void testFindServiceUsingReferenceWithMockedDatabaseRepository() {
        Mockito.when(repository.findByName("1234-1")).thenReturn(List.of(TestData.getService()));
        Assert.assertEquals(1, service.findByName("1234-1").size());
    }

    @Test
    void testFindServiceUsingIdWithMockedDatabaseRepository() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(TestData.getService()));
        Assert.assertEquals("Lumitöitä", service.findById(1L).get().getName());
    }

    @Test
    void testFindAllServicesWithMockedDatabaseRepository() {
        Mockito.when(repository.findAll()).thenReturn(List.of(TestData.getService(), TestData.getService()));
        Assert.assertEquals(2, service.findAll().size());
    }


}
