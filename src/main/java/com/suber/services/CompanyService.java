package com.suber.services;

import com.suber.dto.CompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyDTO save(CompanyDTO companyDTO);

    Optional<CompanyDTO> findById(long id);

    List<CompanyDTO> findAll();

    List<CompanyDTO> findByName(String name);

    List<CompanyDTO> findByBusinessId(String id);

    void deleteById(long id);

    void deleteAll();
}
