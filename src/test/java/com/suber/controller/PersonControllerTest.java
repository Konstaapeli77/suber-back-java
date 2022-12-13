package com.suber.controller;

import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.dto.CompanyDTO;
import com.suber.dto.PersonDTO;
import com.suber.dto.PersonListDTO;
import com.suber.repository.PersonRepository;
import com.suber.util.TestData;
import com.suber.util.mapper.DataMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.crypto.Data;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Autowired
    PersonController controller;

    @Autowired
    PersonRepository repository;

    @Test
    void deletePerson() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));
        ResponseEntity<HttpStatus> status = controller.deletePerson(savedPerson.getId());

        Optional<Person> found = repository.findById(savedPerson.getId());

        Assert.assertFalse(found.isPresent());
        Assert.assertEquals(204, status.getStatusCodeValue());
    }

    @Test
    void getPerson() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

        ResponseEntity<PersonListDTO> personsWithThatName = controller.findByFirstname("Sanna");
        PersonListDTO list = personsWithThatName.getBody();

        Assert.assertTrue(list.getPersons().size() > 0);
        Assert.assertTrue(list.getPersons().get(0).getFirstname().equals("Sanna"));
        Assert.assertEquals(200, personsWithThatName.getStatusCodeValue());
    }

    @Test
    void updatePerson() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));
        savedPerson.setLastname("Väliaho");

        ResponseEntity<PersonDTO> updatedPerson =
                controller.updatePerson(savedPerson.getId(), DataMapper.getInstance().convertToDto(savedPerson));



        Assert.assertEquals(200, updatedPerson.getStatusCodeValue());
        Assert.assertTrue(updatedPerson.getBody().getLastname().equals("Väliaho"));
    }

}
