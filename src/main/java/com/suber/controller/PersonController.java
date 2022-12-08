package com.suber.controller;

import com.suber.data.Person;
import com.suber.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository repository;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllCompanies(@RequestParam(required = false) String lastname) {
        try {
            List<Person> persons = new ArrayList<Person>();

            if (lastname == null)
                repository.findAll().forEach(persons::add);
            else
                repository.findByLastname(lastname).forEach(persons::add);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        Optional<Person> personData = repository.findById(id);

        if (personData.isPresent()) {
            return new ResponseEntity<>(personData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person _person = repository
                    .save(new Person(person.getFirstname(), person.getLastname()));
            return new ResponseEntity<>(_person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Optional<Person> personData = repository.findById(id);

        if (personData.isPresent()) {
            Person _person = personData.get();
            _person.setFirstname(person.getFirstname());
            _person.setLastname(person.getLastname());
            return new ResponseEntity<>(repository.save(_person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/persons")
    public ResponseEntity<HttpStatus> deleteAllPersons() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/persons/lastname")
    public ResponseEntity<List<Person>> findByLastname(@PathVariable("lastname") String lastname) {
        try {
            List<Person> persons = repository.findByLastname(lastname);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/firstname")
    public ResponseEntity<List<Person>> findByFirstname(@PathVariable("firstname") String firstname) {
        try {
            List<Person> persons = repository.findByFirstname(firstname);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
