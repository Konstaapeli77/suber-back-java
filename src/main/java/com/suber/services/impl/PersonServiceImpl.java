package com.suber.services.impl;

import com.suber.data.Person;
import com.suber.dto.PersonDTO;
import com.suber.repository.PersonRepository;
import com.suber.services.PersonService;
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
public class PersonServiceImpl implements PersonService {

    Logger logger = LogManager.getLogger(PersonServiceImpl.class);
    @Autowired
    PersonRepository personRepository;

    public PersonDTO save(PersonDTO personDto) {
        Person person = new Person();
        person.setFirstname(personDto.getFirstname());
        person.setFirstname(personDto.getLastname());
        personRepository.save(person);
        return personDto;
    }

    @Override
    public Optional<PersonDTO> findById(long id) {
        Optional<Person> person = personRepository.findById(id);
        PersonDTO originalPersonDTO = new PersonDTO();
        if (person.isPresent()) {
            originalPersonDTO = DataMapper.personToDTO(person.get());
        } else {
            logger.log(Level.INFO, "Optional<Person> is null!");
        }

        Optional<PersonDTO> personDTO= Optional.of(originalPersonDTO);
        return personDTO;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = new ArrayList<Person>();
        personRepository.findAll().forEach(persons::add);
        for (Person person:persons) {
            PersonDTO personDTO = DataMapper.personToDTO(person);
            personsDTO.add(personDTO);
        }
        return personsDTO;
    }

    @Override
    public void deleteById(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<PersonDTO> findByLastname(String id) {
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = new ArrayList<Person>();
        personRepository.findByLastname(id).forEach(persons::add);
        for (Person person:persons) {
            PersonDTO personDTO = DataMapper.personToDTO(person);
            personsDTO.add(personDTO);
        }
        return personsDTO;
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
