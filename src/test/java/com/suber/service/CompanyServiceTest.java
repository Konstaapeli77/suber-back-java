package com.suber.service;

import com.suber.repository.CompanyRepository;
import com.suber.services.CompanyService;
import com.suber.util.TestData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    CompanyService service;
    @MockBean
    CompanyRepository repository;

    @Test
    void testFindCompanyUsingBusinessIdWithMockedDatabaseRepository() {
        Mockito.when(repository.findByBusinessId("1234-1")).thenReturn(List.of(TestData.getCompany()));
        Assert.assertEquals(1, service.findByBusinessId("1234-1").size());
    }

    @Test
    void testFindCompanyUsingIdWithMockedDatabaseRepository() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(TestData.getCompany()));
        Assert.assertEquals("12314-1", service.findById(1).get().getBusinessId());
    }

    @Test
    void testFindAllCompaniesWithMockedDatabaseRepository() {
        Mockito.when(repository.findAll()).thenReturn(List.of(TestData.getCompany(), TestData.getCompany()));
        Assert.assertEquals(2, service.findAll().size());
    }

    @Test
    void testFindCompanyByNameWithMockedDatabaseRepository() {
        Mockito.when(repository.findByName("Valtor Oy")).thenReturn(List.of(TestData.getCompany()));
        Assert.assertEquals("12314-1", service.findByName("Valtor Oy").get(0).getBusinessId());
    }

    @Test
    void testSaveCompanyToMockedDatabaseRepository() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(TestData.getCompany()));
        Mockito.when(repository.save(any())).thenReturn(TestData.getCompany());
        Assert.assertEquals(TestData.getCompanyDTO(), service.save(TestData.getCompanyDTO()));
    }

    /*
    @Test
    void testDeleteCompanyByIdWithMockedDatabaseRepository() {
        CompanyRepository company = mock(CompanyRepository.class);
        ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().doThrow(new RuntimeException()).when(company).findById(valueCapture.capture());
        company.findById(1L);

        Assert.assertEquals(1L, valueCapture.getValue().longValue());

        /*
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                System.out.println("called with arguments: " + Arrays.toString(args));
                return null;
            }
        }).when(repository).findById(1L);

        Assert.assertEquals(1L, );
    }

     */

}
