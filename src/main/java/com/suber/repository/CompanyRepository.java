package com.suber.repository;

import com.suber.data.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findById(@Param("id") long id);
    List<Company> findByName(@Param("name") String name);
    List<Company> findByBusinessId(@Param("businessId") String name);

}
