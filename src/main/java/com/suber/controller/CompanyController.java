package com.suber.controller;

import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.exception.PersonNotFoundException;
import com.suber.repository.CompanyRepository;
import com.suber.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyRepository repository;

    CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/companies")
    List<Company> all() {
        return repository.findAll();
    }

    @PostMapping("/companies")
    Company newCompany(@RequestBody Company newCompany) {
        return repository.save(newCompany);
    }

    @GetMapping("/companies/{id}")
    Company one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/companies/{id}")
    Company replaceCompany(@RequestBody Company newCompany, @PathVariable Long id) {

        return repository.findById(id)
                .map(company -> {
                    company.setName(newCompany.getName());
                    company.setBusinessId(newCompany.getBusinessId());
                    return repository.save(company);
                })
                .orElseGet(() -> {
                    newCompany.setId(id);
                    return repository.save(newCompany);
                });
    }

    @DeleteMapping("/companies/{id}")
    void deleteCompany(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
