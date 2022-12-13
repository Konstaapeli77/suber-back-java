package com.suber.controller;

import com.suber.dto.PersonDTO;
import com.suber.dto.PersonListDTO;
import com.suber.services.PersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<PersonListDTO> getAllPersons(@RequestParam(required = false) String lastname) {
        try {
            List<PersonDTO> persons = new ArrayList<PersonDTO>();

            if (lastname == null)
                personService.findAll().forEach(persons::add);
            else
                personService.findByLastname(lastname).forEach(persons::add);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //return new ResponseEntity<>(persons, HttpStatus.OK);
            return new ResponseEntity<>(new PersonListDTO(persons), HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error in getAllPersons: " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") long id) {
        Optional<PersonDTO> personData = personService.findById(id);

        if (personData.isPresent()) {
            return new ResponseEntity<>(personData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO person) {
        try {
            PersonDTO _person = personService
                    .save(person);
            return new ResponseEntity<>(_person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO person) {
        Optional<PersonDTO> personData = personService.findById(id);

        if (personData.isPresent()) {
            PersonDTO _person = personData.get();
            _person.setFirstname(person.getFirstname());
            _person.setLastname(person.getLastname());
            return new ResponseEntity<>(personService.save(_person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        try {
            personService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/persons")
    public ResponseEntity<HttpStatus> deleteAllPersons() {
        try {
            personService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/persons/name")
    public ResponseEntity<PersonListDTO> findByFirstname(@PathVariable("firstname") String firstname) {
        try {
            List<PersonDTO> persons = personService.findByLastname(firstname);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new PersonListDTO(persons), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/lastname")
    public ResponseEntity<PersonListDTO> findByLastname(@PathVariable("lastname") String lastname) {
        try {
            List<PersonDTO> persons = personService.findByLastname(lastname);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new PersonListDTO(persons), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
