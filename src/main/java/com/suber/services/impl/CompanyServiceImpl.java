package com.suber.services.impl;

import com.jayway.jsonpath.JsonPath;
import com.suber.data.Company;
import com.suber.dto.CompanyDTO;
import com.suber.repository.CompanyRepository;
import com.suber.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository repository;

    public CompanyDTO save(CompanyDTO companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setBusinessId(companyDto.getBusinessId());
        repository.save(company);
        return companyDto;
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();
        repository.findAll().forEach(companies::add);
        for (Company company:companies) {
            CompanyDTO companyDTO = new CompanyDTO(company.getName(), company.getBusinessId());
            companiesDTO.add(companyDTO);
        }
        return companiesDTO;
    }

    @Override
    public List<CompanyDTO> findByName(String name) {
        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();
        repository.findByName(name).forEach(companies::add);
        for (Company company:companies) {
            CompanyDTO companyDTO = new CompanyDTO(company.getName(), company.getBusinessId());
            companiesDTO.add(companyDTO);
        }
        return companiesDTO;
    }

    @Override
    public List<CompanyDTO> findByBusinessId(String id) {
        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();
        repository.findByBusinessId(id).forEach(companies::add);
        for (Company company:companies) {
            CompanyDTO companyDTO = new CompanyDTO(company.getName(), company.getBusinessId());
            companiesDTO.add(companyDTO);
        }
        return companiesDTO;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Optional<CompanyDTO> findById(long id) {
        Optional<Company> company = repository.findById(id);
        CompanyDTO originalCompanyDTO = new CompanyDTO(company.get().getName(), company.get().getBusinessId());
        Optional<CompanyDTO> companyDTO= Optional.of(originalCompanyDTO);
        return companyDTO;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
