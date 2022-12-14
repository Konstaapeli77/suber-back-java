package com.suber.controller;

import com.suber.dto.CompanyDTO;
import com.suber.dto.CompanyListDTO;
import com.suber.dto.PersonDTO;
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

@RestController
public class CompanyController {

    Logger logger = LogManager.getLogger(CompanyController.class);

    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<CompanyListDTO> getAllCompanies(@RequestParam(required = false) String name) {
        try {
            List<CompanyDTO> companies = new ArrayList<CompanyDTO>();

            if (name == null) {
                companyService.findAll().forEach(companies::add);
            } else {
                companyService.findByName(name).forEach(companies::add);
            }

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

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
            logger.info("post-1");
            CompanyDTO _company = companyService.save(company);
            logger.info("post-2");
            return new ResponseEntity<>(_company, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error in create company: " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") long id, @RequestBody CompanyDTO company) {
        Optional<CompanyDTO> companyData = companyService.updateCompany(id, company);

        if (companyData.isPresent()) {
            return new ResponseEntity<>(companyData.get(), HttpStatus.OK);
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
