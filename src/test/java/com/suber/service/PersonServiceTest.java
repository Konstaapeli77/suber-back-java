package com.suber.service;

import com.suber.repository.PersonRepository;
import com.suber.services.PersonService;
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
public class PersonServiceTest {

    @Autowired
    PersonService service;
    @MockBean
    PersonRepository repository;

    @Test
    void testFindServiceUsingReferenceWithMockedDatabaseRepository() {
        Mockito.when(repository.findByFirstname("1234-1")).thenReturn(List.of(TestData.getPerson()));
        Assert.assertEquals(1, service.findByFirstname("1234-1").size());
    }

    @Test
    void testFindServiceUsingIdWithMockedDatabaseRepository() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(TestData.getPerson()));
        Assert.assertEquals("Mikko", service.findById(1).get().getFirstname());
    }

    @Test
    void testFindAllServicesWithMockedDatabaseRepository() {
        Mockito.when(repository.findAll()).thenReturn(List.of(TestData.getPerson(), TestData.getPerson()));
        Assert.assertEquals(2, service.findAll().size());
    }


}
