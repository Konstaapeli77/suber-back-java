package com.suber.repository;

import java.util.List;
import com.suber.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstname(@Param("firstname") String firstname);

    List<Person> findByLastname(@Param("lastname") String lastname);
}
