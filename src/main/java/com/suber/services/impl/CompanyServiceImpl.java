package com.suber.services.impl;

import com.suber.data.Company;
import com.suber.dto.CompanyDTO;
import com.suber.repository.CompanyRepository;
import com.suber.services.CompanyService;
import com.suber.util.mapper.DataMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
public class CompanyServiceImpl implements CompanyService {

    Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
    CompanyRepository repository;

    @Autowired
    DataMapper dataMapper;

    public CompanyDTO save(CompanyDTO companyDTO) {
        logger.log(Level.INFO, "company: " + companyDTO);
        Company company = dataMapper.convertToEntity(companyDTO);
        Company company2 = repository.save(company);
        logger.log(Level.INFO, "company2: " + company2);
        return companyDTO;
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();
        repository.findAll().forEach(companies::add);

        for (Company company:companies) {
            CompanyDTO companyDTO = dataMapper.convertToDto(company);
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
            CompanyDTO companyDTO = dataMapper.convertToDto(company);
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
            CompanyDTO companyDTO = dataMapper.convertToDto(company);
            companiesDTO.add(companyDTO);
        }
        return companiesDTO;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    // TODO - tarkista tämä
    public Optional<CompanyDTO> findById(long id) {

        Optional<Company> company = repository.findById(id);
        CompanyDTO originalCompanyDTO = new CompanyDTO();
        if (company.isPresent()) {
            originalCompanyDTO = dataMapper.convertToDto(company.get());
        } else {
            logger.log(Level.INFO, "No value present... again");
        }
        Optional<CompanyDTO> companyDTO= Optional.of(originalCompanyDTO);
        return companyDTO;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
