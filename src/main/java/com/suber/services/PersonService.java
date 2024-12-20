package com.suber.services;

import com.suber.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    PersonDTO save(PersonDTO personDTO);

    Optional<PersonDTO> findById(long id);

    Optional<PersonDTO> updatePerson(long id, PersonDTO person);

    List<PersonDTO> findAll();

    List<PersonDTO> findByLastname(String lastname);

    List<PersonDTO> findByFirstname(String firstname);

    void deleteById(long id);

    void deleteAll();
}
