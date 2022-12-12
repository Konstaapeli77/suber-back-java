package com.suber.controller;

import com.suber.controller.wrapper.CompanyList;
import com.suber.data.Company;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CompanyControllerTest.class);
    @Autowired
    CompanyController controller;

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
        /*
        final String baseUrl = "http://localhost:" + port + "/companies/";
        URI uri = new URI(baseUrl);
        Company company = new Company("Valio Oy", "123111-1");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Company> request = new HttpEntity<>(company, headers);
        ResponseEntity<Company> result = this.restTemplate.postForEntity(uri, request, Company.class);

        Assert.assertEquals(201, result.getStatusCodeValue());

         */
    }


    @Test
    public void companiesGetShouldSucceed() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/companies/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ResponseEntity<CompanyList> result = this.restTemplate.getForEntity(uri, CompanyList.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
    }



}
