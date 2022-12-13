package com.suber.services;

import com.suber.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    PersonDTO save(PersonDTO personDTO);

    Optional<PersonDTO> findById(long id);

    List<PersonDTO> findAll();

    List<PersonDTO> findByLastname(String lastname);

    void deleteById(long id);

    void deleteAll();
}
