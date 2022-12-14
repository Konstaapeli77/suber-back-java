package com.suber.controller;

import com.suber.data.Company;
import com.suber.data.Company;
import com.suber.dto.CompanyDTO;
import com.suber.dto.CompanyListDTO;
import com.suber.dto.CompanyDTO;
import com.suber.dto.CompanyListDTO;
import com.suber.exception.NoSuchEntityException;
import com.suber.repository.CompanyRepository;
import com.suber.util.TestData;
import com.suber.util.mapper.DataMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        ResponseEntity<CompanyListDTO> result2 = controller.getAllCompanies();
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

    // TODO - jatka tästä ottamalla kopio service-controllerista

    @Test
    void serviceShouldBeDeletedAndReturnedSuccess() {

        Company savedCompany = repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));

        ResponseEntity<HttpStatus> status = controller.deleteCompany(savedCompany.getId());

        Optional<Company> found = repository.findById(savedCompany.getId());

        Assert.assertFalse(found.isPresent());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void allCompanysShouldBeDeletedAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        List<Company> found = repository.findAll();

        ResponseEntity<HttpStatus> status = controller.deleteAllCompanies();

        List<Company> foundDeleted = repository.findAll();

        Assert.assertTrue(found.size() > 0);
        Assert.assertEquals(0, foundDeleted.size());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void serviceShouldBeFoundWithNameAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));

        ResponseEntity<CompanyListDTO> servicesWithThatName = controller.findByName("Valtor Oy");
        CompanyListDTO list = servicesWithThatName.getBody();

        Assert.assertTrue(list.getCompanies().size() > 0);
        Assert.assertTrue(list.getCompanies().get(0).getName().equals("Valtor Oy"));
        Assert.assertEquals(HttpStatus.OK, servicesWithThatName.getStatusCode());
    }

    @Test
    void serviceShouldNotBeFoundWithFirstnameAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));

        NoSuchEntityException thrown = assertThrows(NoSuchEntityException.class,
                () -> controller.findByName("Jaakko")
        );

        Assertions.assertTrue(thrown.getMessage().contains("Company with name 'Jaakko' was not found"));
    }

    @Test
    void serviceShouldBeFoundWithNeAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));

        ResponseEntity<CompanyListDTO> servicesWithThatName = controller.findByName("Valtor Oy");
        CompanyListDTO list = servicesWithThatName.getBody();

        Assert.assertTrue(list.getCompanies().size() > 0);
        Assert.assertTrue(list.getCompanies().get(0).getName().equals("Valtor Oy"));
        Assert.assertEquals(HttpStatus.OK, servicesWithThatName.getStatusCode());
    }

    @Test
    void serviceWithAddressShouldBeUpdatedAndReturnedSuccess() {

        Company savedCompany = repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        savedCompany.setName("Väliaho");

        CompanyDTO serviceDTOFromCompany = DataMapper.getInstance().convertToDto(savedCompany);
        ResponseEntity<CompanyDTO> updatedCompany =
                controller.updateCompany(savedCompany.getId(), serviceDTOFromCompany);

        Assert.assertEquals(HttpStatus.OK, updatedCompany.getStatusCode());
        Assert.assertTrue(updatedCompany.getBody().getName().equals("Väliaho"));
    }

    @Test
    void serviceShouldBeUpdatedAndReturnedSuccess() {

        Company savedCompany = repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        savedCompany.setName("Väliaho");

        CompanyDTO serviceDTOFromCompany = DataMapper.getInstance().convertToDto(savedCompany);
        ResponseEntity<CompanyDTO> updatedCompany =
                controller.updateCompany(savedCompany.getId(), serviceDTOFromCompany);

        Assert.assertEquals(HttpStatus.OK, updatedCompany.getStatusCode());
        Assert.assertTrue(updatedCompany.getBody().getName().equals("Väliaho"));
    }

    @Test
    void serviceShouldNotBeUpdatedAndReturnFailed() {

        long wrongId = 10000;

        Company savedCompany = repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        savedCompany.setName("Väliaho");

        CompanyDTO serviceDTOFromCompany = DataMapper.getInstance().convertToDto(savedCompany);

        NoSuchEntityException thrown = assertThrows(NoSuchEntityException.class,
                () -> controller.updateCompany(wrongId, serviceDTOFromCompany)
        );

        assertTrue(thrown.getMessage().contentEquals("Company with id '10000' was not found"));
    }

    @Test
    void getAllCompanyAndReturnedSuccess() {

        repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));
        List<Company> found = repository.findAll();

        ResponseEntity<CompanyListDTO> listOfCompanys = controller.getAllCompanies();

        Assert.assertEquals(HttpStatus.OK, listOfCompanys.getStatusCode());
        Assert.assertTrue(listOfCompanys.getBody().getCompanies().size() > 0);

    }

    @Test
    void createCompanyAndReturnedSuccess() {

        ResponseEntity<CompanyDTO> createdCompany = controller.createCompany(TestData.getCompanyDTO());

        Optional<Company> savedCompany = repository.findById(createdCompany.getBody().getId());

        Assert.assertTrue(savedCompany.isPresent());
        Assert.assertEquals(HttpStatus.CREATED, createdCompany.getStatusCode());

    }

    @Test
    void getCompanyByIdAndReturnedSuccess() {

        Company savedCompany = repository.save(DataMapper.getInstance().convertToEntity(TestData.getCompanyDTO()));

        ResponseEntity<CompanyDTO> foundCompany = controller.getCompanyById(savedCompany.getId());

        Assert.assertEquals(savedCompany.getId(), foundCompany.getBody().getId());
        Assert.assertEquals(HttpStatus.OK, foundCompany.getStatusCode());

    }

    @Test
    public void exceptionThrownFromGetCompanyById() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void resourceNotFoundResponseSentFromGetCompanyById() {
        long wrongId = 100000;

        NoSuchEntityException thrown = assertThrows(NoSuchEntityException.class,
                () -> controller.getCompanyById(wrongId)
        );

        Assertions.assertEquals("Company with id '100000' was not found", thrown.getMessage());
    }


}
