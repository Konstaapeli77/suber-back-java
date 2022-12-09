package com.suber.services.impl;

import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.dto.CompanyDTO;
import com.suber.dto.PersonDTO;
import com.suber.repository.PersonRepository;
import com.suber.services.CompanyService;
import com.suber.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonServiceImpl implements PersonService {

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
        PersonDTO originalPersonDTO = new PersonDTO(person.get().getFirstname(), person.get().getLastname());
        Optional<PersonDTO> personDTO= Optional.of(originalPersonDTO);
        return personDTO;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = new ArrayList<Person>();
        personRepository.findAll().forEach(persons::add);
        for (Person company:persons) {
            PersonDTO companyDTO = new PersonDTO(company.getFirstname(), company.getLastname());
            personsDTO.add(companyDTO);
        }
        return personsDTO;
    }

    @Override
    public List<PersonDTO> findByFirstname(String firstname) {
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = new ArrayList<Person>();
        personRepository.findByFirstname(firstname).forEach(persons::add);
        for (Person person:persons) {
            PersonDTO companyDTO = new PersonDTO(person.getFirstname(), person.getLastname());
            personsDTO.add(companyDTO);
        }
        return personsDTO;
    }

    @Override
    public void deleteById(long id) {
        personRepository.deleteById(id);
    }

    /*
    public Optional<PersonDTO> findByLastname(String lastname) {
        Optional<PersonDTO> persons = personRepository.findByLastname(lastname);
        PersonDTO originalCompanyDTO = new PersonDTO(person.get().getFirstname(), person.get().getLastname());
        Optional<PersonDTO> personDTO= Optional.of(originalCompanyDTO);
        return personDTO;
    }
*/
    @Override
    public List<PersonDTO> findByLastname(String id) {
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = new ArrayList<Person>();
        personRepository.findByLastname(id).forEach(persons::add);
        for (Person person:persons) {
            PersonDTO personDTO = new PersonDTO(person.getFirstname(), person.getLastname());
            personsDTO.add(personDTO);
        }
        return personsDTO;
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
