package com.suber.controller;

import com.suber.data.Person;
import com.suber.exception.PersonNotFoundException;
import com.suber.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons")
    List<Person> all() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    Person newEmployee(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persons/{id}")
    Person replaceEmployee(@RequestBody Person newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
