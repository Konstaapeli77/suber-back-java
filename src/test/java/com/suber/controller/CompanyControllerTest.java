package com.suber.controller;

import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.dto.CompanyDTO;
import com.suber.dto.CompanyListDTO;
import com.suber.dto.PersonDTO;
import com.suber.repository.CompanyRepository;
import com.suber.util.TestData;
import com.suber.util.mapper.DataMapper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(CompanyControllerTest.class);
    @Autowired
    CompanyController controller;

    @Autowired
    CompanyRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void companiesPostShouldSucceed() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/companies/";
        URI uri = new URI(baseUrl);
        CompanyDTO company = CompanyDTO.builder()
                //.id(4)
                .name("Valio Oy")
                .address(TestData.getAddressDTO())
                .businessId("123441-1")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CompanyDTO> request = new HttpEntity<>(company, headers);
        ResponseEntity<CompanyDTO> result = this.restTemplate.postForEntity(uri, request, CompanyDTO.class);

        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void companiesGetShouldSucceed() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/companies/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ResponseEntity<CompanyListDTO> result = this.restTemplate.getForEntity(uri, CompanyListDTO.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }


    @Test
    void testGetAllCompaniesController() throws Exception {

        final String baseUrl = "http://localhost:" + port + "/companies/";
        URI uri = new URI(baseUrl);
        CompanyDTO company = CompanyDTO.builder()
                //.id(4)
                .name("Valiotta Oy")
                .address(TestData.getAddressDTO())
                .businessId("1233241-1")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CompanyDTO> request = new HttpEntity<>(company, headers);
        ResponseEntity<CompanyDTO> result = this.restTemplate.postForEntity(uri, request, CompanyDTO.class);

        ResponseEntity<CompanyListDTO> result2 = controller.getAllCompanies(null);
        CompanyListDTO list = result2.getBody();

        Assert.assertTrue(list.getCompanies().size() > 0);
        Assert.assertEquals(201, result.getStatusCodeValue());
        Assert.assertEquals(200, result2.getStatusCodeValue());
    }


    @Test
    void companyShouldBeUpdatedAndReturnedSuccess() {

        Company savedCompany = repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        savedCompany.setName("Väliaho");

        CompanyDTO companyDTOFromCompany = DataMapper.getInstance().convertToDto(savedCompany);
        ResponseEntity<CompanyDTO> updatedCompany =
                controller.updateCompany(savedCompany.getId(), companyDTOFromCompany);

        Assert.assertEquals(HttpStatus.OK, updatedCompany.getStatusCode());
        Assert.assertTrue(updatedCompany.getBody().getName().equals("Väliaho"));
    }

    @Test
    void deleteCompany() {

        CompanyDTO companyDTO = CompanyDTO.builder()
                .name("Sisu Oy")
                .address(TestData.getAddressDTO())
                .businessId("333241-4")
                .build();

        Company companyResultX = repository.save(DataMapper.getInstance().convertToEntity(companyDTO));

        controller.deleteCompany(companyResultX.getId());

        Optional<Company> found = repository.findById(companyResultX.getId());

        Assert.assertFalse(found.isPresent());
    }

}
