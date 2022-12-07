package com.suber.repository;

import com.suber.data.Company;
import com.suber.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "company", path = "company")
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Person> findByName(@Param("name") String name);

}
