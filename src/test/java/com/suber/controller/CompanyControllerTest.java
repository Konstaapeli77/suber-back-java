package com.suber.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CompanyControllerTest {

    @Autowired
    CompanyController companyController;

    /*
    @Test
    void health() {
        assertEquals( "HEALTH CHECK OK!", companyController.getCompanyById(1));
    }

    @Test
    void version() {
        assertEquals( "The actual version is 1.0.0", companyController.version());
    }

    @Test
    void nationsLength() {
        Integer nationsLength = companyController.getRandomNations().size();
        assertEquals(10, nationsLength);
    }

    @Test
    void currenciesLength() {
        Integer currenciesLength = companyController.getRandomCurrencies().size();
        assertEquals(20, currenciesLength);
    }

    */

}
