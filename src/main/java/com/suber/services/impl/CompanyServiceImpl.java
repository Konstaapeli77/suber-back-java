package com.suber.services.impl;

import com.suber.data.Company;
import com.suber.dto.CompanyDTO;
import com.suber.exception.EntityAlreadyExistsException;
import com.suber.exception.NoSuchEntityException;
import com.suber.repository.CompanyRepository;
import com.suber.services.CompanyService;
import com.suber.util.mapper.DataMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CompanyServiceImpl implements CompanyService {

    Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
    CompanyRepository repository;

    public CompanyDTO save(CompanyDTO companyDTO) {

        Company existingCompany = repository.findById(companyDTO.getId())
                .orElse(null);

        if (existingCompany == null) {
            Company ext = DataMapper.getInstance().convertToEntity(companyDTO);
            existingCompany = repository.save(ext);
        }
        else
            throw new EntityAlreadyExistsException(
                    "Company with id '" + companyDTO.getId() + "' exists!");

        return DataMapper.getInstance().convertToDto(existingCompany);
    }

    @Override
    public List<CompanyDTO> findAll() {

        /*

        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();

        repository.findAll().forEach(companies::add);

        for (Company company:companies) {
            CompanyDTO companyDTO = DataMapper.getInstance().convertToDto(company);
            companiesDTO.add(companyDTO);
        }
        return companiesDTO;
         */

        return repository.findAll().stream()
                .map(company -> DataMapper.getInstance().convertToDto(company))
                .collect(Collectors.toList());


    }

    @Override
    public List<CompanyDTO> findByName(String name) {
        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();
        repository.findByName(name).forEach(companies::add);
        if (companies.size() == 0) {
            throw new NoSuchEntityException("Company with name '" + name + "' was not found ");
        }
        for (Company company:companies) {
            CompanyDTO companyDTO = DataMapper.getInstance().convertToDto(company);
            companiesDTO.add(companyDTO);
        }
        return companiesDTO;
    }

    // TODO => Stream?
    @Override
    public List<CompanyDTO> findByBusinessId(String id) {
        List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
        List<Company> companies = new ArrayList<Company>();
        repository.findByBusinessId(id).forEach(companies::add);
        if (companies.size() == 0) {
            throw new NoSuchEntityException("Company with business id '" + id + "' was not found ");
        }
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
    @Override
    public Optional<CompanyDTO> findById(long id) {

        Company company = repository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("Company with id '" + id + "' was not found"));

        Optional<CompanyDTO> companyDTO= Optional.of(DataMapper.getInstance().convertToDto(company));
        return companyDTO;
    }

    @Override
    public Optional<CompanyDTO> updateCompany(long id, CompanyDTO companyDTO) {
        Company company = repository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("Company with id '" + id + "' was not found"));

        company.setName(companyDTO.getName());
        company.setBusinessId(companyDTO.getBusinessId());

        if (companyDTO.getAddress() == null) {
            company.setAddress(null);
        } else {
            company.setAddress(DataMapper.getInstance().convertToEntity(companyDTO.getAddress()));
        }
        Company updatedCompanyEntity = repository.save(company);

        Optional<CompanyDTO> companyDTOResult = Optional.of(
                DataMapper.getInstance().convertToDto(updatedCompanyEntity));

        return companyDTOResult;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
