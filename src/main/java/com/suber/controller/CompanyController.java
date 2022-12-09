package com.suber.controller;

import com.suber.controller.wrapper.CompanyList;
import com.suber.data.Company;
import com.suber.dto.CompanyDTO;
import com.suber.repository.CompanyRepository;
import com.suber.services.CompanyService;
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

    @Autowired
    CompanyRepository repository;

    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<CompanyList> getAllCompanies(@RequestParam(required = false) String name) {
        try {
            List<Company> companies = new ArrayList<Company>();

            if (name == null)
                repository.findAll().forEach(companies::add);
            else
                repository.findByName(name).forEach(companies::add);

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //return new ResponseEntity<>(companies, HttpStatus.OK);
            return new ResponseEntity<>(new CompanyList(companies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
        Optional<Company> companyData = repository.findById(id);

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
                    .save(new CompanyDTO(company.getName(), company.getBusinessId()));
            return new ResponseEntity<>(_company, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
        Optional<Company> companyData = repository.findById(id);

        if (companyData.isPresent()) {
            Company _company = companyData.get();
            _company.setName(company.getName());
            _company.setBusinessId(company.getBusinessId());
            return new ResponseEntity<>(repository.save(_company), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/companies")
    public ResponseEntity<HttpStatus> deleteAllCompanies() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/companies/name")
    public ResponseEntity<CompanyList> findByName(@PathVariable("name") String name) {
        try {
            List<Company> companies = repository.findByName(name);

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new CompanyList(companies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companies/businessId")
    public ResponseEntity<CompanyList> findByBusinessId(@PathVariable("businessId") String businessId) {
        try {
            List<Company> companies = repository.findByBusinessId(businessId);

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new CompanyList(companies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
