package com.suber.services.impl;

import com.suber.data.Company;
import com.suber.dto.CompanyDTO;
import com.suber.repository.CompanyRepository;
import com.suber.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


}
