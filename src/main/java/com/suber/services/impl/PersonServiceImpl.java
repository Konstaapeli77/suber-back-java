package com.suber.services.impl;

import com.suber.data.Person;
import com.suber.dto.PersonDTO;
import com.suber.exception.ResourceNotFoundException;
import com.suber.repository.PersonRepository;
import com.suber.services.PersonService;
import com.suber.util.mapper.DataMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    Logger logger = LogManager.getLogger(PersonServiceImpl.class);
    @Autowired
    PersonRepository personRepository;

    public PersonDTO save(PersonDTO personDto) {
        Person person = new Person();
        person.setFirstname(personDto.getFirstname());
        person.setLastname(personDto.getLastname());
        return DataMapper.getInstance().convertToDto(personRepository.save(person));
    }

    @Override
    public Optional<PersonDTO> findById(long id) throws ResourceNotFoundException {
        Optional<Person> person = personRepository.findById(id);
        PersonDTO originalPersonDTO = null;
        if (person.isPresent()) {
            originalPersonDTO = DataMapper.getInstance().convertToDto(person.get());
        } else {
            return Optional.empty();
        }

        Optional<PersonDTO> personDTO= Optional.of(originalPersonDTO);
        return personDTO;
    }

    @Override
    public Optional<PersonDTO> updatePerson(long id, PersonDTO updatedPerson) throws ResourceNotFoundException {
        Optional<Person> person = personRepository.findById(id);

        PersonDTO result = null;
        if (person.isPresent()) {
            Person personEntity = person.get();
            personEntity.setFirstname(updatedPerson.getFirstname());
            personEntity.setLastname(updatedPerson.getLastname());
            if (updatedPerson.getAddress() == null) {
                personEntity.setAddress(null);
            } else {
                personEntity.setAddress(DataMapper.getInstance().convertToEntity(updatedPerson.getAddress()));
            }
            Person updatedPersonEntity = personRepository.save(personEntity);
            result = DataMapper.getInstance().convertToDto(updatedPersonEntity);
        } else {
            return Optional.empty();
        }

        Optional<PersonDTO> personDTO= Optional.of(result);
        return personDTO;
    }

    @Override
    public List<PersonDTO> findAll() {
/*      Functional
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = new ArrayList<Person>();
        personRepository.findAll().forEach(persons::add);
        for (Person person:persons) {
            PersonDTO personDTO = DataMapper.getInstance().convertToDto(person);
            personsDTO.add(personDTO);
        }
        return personsDTO;
*/

        return personRepository.findAll().stream()
                .map(person -> DataMapper.getInstance().convertToDto(person))
                .collect(Collectors.toList());
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
            PersonDTO personDTO = DataMapper.getInstance().convertToDto(person);
            personsDTO.add(personDTO);
        }
        return personsDTO;
    }

    @Override
    public List<PersonDTO> findByFirstname(String id) {
        List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
        List<Person> persons = new ArrayList<Person>();
        personRepository.findByFirstname(id).forEach(persons::add);
        for (Person person:persons) {
            PersonDTO personDTO = DataMapper.getInstance().convertToDto(person);
            personsDTO.add(personDTO);
        }
        return personsDTO;
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
