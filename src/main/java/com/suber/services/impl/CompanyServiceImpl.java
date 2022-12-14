package com.suber.services.impl;

import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.dto.AddressDTO;
import com.suber.dto.CompanyDTO;
import com.suber.dto.PersonDTO;
import com.suber.exception.ResourceNotFoundException;
import com.suber.repository.CompanyRepository;
import com.suber.services.CompanyService;
import com.suber.util.mapper.DataMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class CompanyServiceImpl implements CompanyService {

    Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
    CompanyRepository repository;

//    @Autowired
//    DataMapper dataMapper;

    public CompanyDTO save(CompanyDTO companyDTO) {
        Company company = DataMapper.getInstance().convertToEntity(companyDTO);
        Company company2 = repository.save(company);
        return companyDTO;
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();
        repository.findAll().forEach(companies::add);
        for (Company company:companies) {
            CompanyDTO companyDTO = DataMapper.getInstance().convertToDto(company);
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
            CompanyDTO companyDTO = DataMapper.getInstance().convertToDto(company);
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
            CompanyDTO companyDTO = DataMapper.getInstance().convertToDto(company);
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
            originalCompanyDTO = DataMapper.getInstance().convertToDto(company.get());
        } else {
            return Optional.empty();
        }
        Optional<CompanyDTO> companyDTO= Optional.of(originalCompanyDTO);
        return companyDTO;
    }

    @Override
    public Optional<CompanyDTO> updateCompany(long id, CompanyDTO companyDTO) {
        Optional<Company> company = repository.findById(id);

        CompanyDTO result = null;
        if (company.isPresent()) {
            Company companyEntity = company.get();
            companyEntity.setName(companyDTO.getName());
            companyEntity.setBusinessId(companyDTO.getBusinessId());

            if (companyDTO.getAddress() == null) {
                companyEntity.setAddress(null);
            } else {
                companyEntity.setAddress(DataMapper.getInstance().convertToEntity(companyDTO.getAddress()));
            }
            Company updatedCompanyEntity = repository.save(companyEntity);
            result = DataMapper.getInstance().convertToDto(updatedCompanyEntity);
        } else {
            return Optional.empty();
        }

        Optional<CompanyDTO> companyDTOResult = Optional.of(result);
        return companyDTOResult;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
