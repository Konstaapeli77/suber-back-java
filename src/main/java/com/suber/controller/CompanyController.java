package com.suber.controller;

import com.suber.dto.CompanyDTO;
import com.suber.dto.CompanyListDTO;
import com.suber.services.CompanyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CompanyController {

    Logger logger = LogManager.getLogger(CompanyController.class);


    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<CompanyListDTO> getAllCompanies(@RequestParam(required = false) String name) {
        try {
            logger.log(Level.INFO, "getAllCompanies() - start");
            List<CompanyDTO> companies = new ArrayList<CompanyDTO>();
            logger.log(Level.INFO, "1");

            if (name == null) {
                logger.log(Level.INFO, "1a");
                companyService.findAll().forEach(companies::add);
                logger.log(Level.INFO, "2");
            } else {
                companyService.findByName(name).forEach(companies::add);
                logger.log(Level.INFO, "3");
            }


            if (companies.isEmpty()) {
                logger.log(Level.INFO, "4");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.log(Level.INFO, "companies: " + companies);

            //return new ResponseEntity<>(companies, HttpStatus.OK);
            logger.log(Level.INFO, "getAllCompanies() - end");
            return new ResponseEntity<>(new CompanyListDTO(companies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable("id") long id) {
        Optional<CompanyDTO> companyData = companyService.findById(id);

        if (companyData.isPresent()) {
            return new ResponseEntity<>(companyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO company) {
        try {
            CompanyDTO _company = companyService
                    .save(company);
            return new ResponseEntity<>(_company, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") long id, @RequestBody CompanyDTO company) {
        Optional<CompanyDTO> companyData = companyService.findById(id);

        if (companyData.isPresent()) {
            CompanyDTO _company = companyData.get();
            _company.setName(company.getName());
            _company.setBusinessId(company.getBusinessId());
            return new ResponseEntity<>(companyService.save(_company), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
        try {
            companyService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/companies")
    public ResponseEntity<HttpStatus> deleteAllCompanies() {
        try {
            companyService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/companies/name")
    public ResponseEntity<CompanyListDTO> findByName(@PathVariable("name") String name) {
        try {
            List<CompanyDTO> companies = companyService.findByName(name);

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new CompanyListDTO(companies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companies/businessId")
    public ResponseEntity<CompanyListDTO> findByBusinessId(@PathVariable("businessId") String businessId) {
        try {
            List<CompanyDTO> companies = companyService.findByBusinessId(businessId);

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new CompanyListDTO(companies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
