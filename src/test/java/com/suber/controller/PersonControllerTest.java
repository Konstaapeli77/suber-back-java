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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Autowired
    PersonController controller;

    @Autowired
    PersonRepository repository;

    @Test
    void personShouldBeDeletedAndReturnedSuccess() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

        ResponseEntity<HttpStatus> status = controller.deletePerson(savedPerson.getId());

        Optional<Person> found = repository.findById(savedPerson.getId());

        Assert.assertFalse(found.isPresent());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void allPersonsShouldBeDeletedAndReturnedSuccess() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        PersonDTO person2DTO = PersonDTO.builder()
                .firstname("Valtteri")
                .lastname("Virtanen")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));
        Person savedPerson2 = repository.save(DataMapper.getInstance().convertToEntity(person2DTO));
        List<Person> found = repository.findAll();
        System.out.println("size: " + found.size());

        ResponseEntity<HttpStatus> status = controller.deleteAllPersons();

        List<Person> foundDeleted = repository.findAll();
        System.out.println("size: " + foundDeleted.size());

        Assert.assertTrue(found.size() > 0);
        Assert.assertEquals(0, foundDeleted.size());
        Assert.assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
    }

    @Test
    void personShouldBeFoundWithFirstnameAndReturnedSuccess() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

        ResponseEntity<PersonListDTO> personsWithThatName = controller.findByFirstname("Sanna");
        PersonListDTO list = personsWithThatName.getBody();

        Assert.assertTrue(list.getPersons().size() > 0);
        Assert.assertTrue(list.getPersons().get(0).getFirstname().equals("Sanna"));
        Assert.assertEquals(HttpStatus.OK, personsWithThatName.getStatusCode());
    }

    @Test
    void personShouldNotBeFoundWithFirstnameAndReturnedSuccess() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

        ResponseEntity<PersonListDTO> personsWithThatName = controller.findByFirstname("Jaakko");
        PersonListDTO list = personsWithThatName.getBody();

        Assert.assertTrue(list == null);
        Assert.assertEquals(HttpStatus.NO_CONTENT, personsWithThatName.getStatusCode());
    }

    @Test
    void personShouldBeFoundWithLastnameAndReturnedSuccess() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

        ResponseEntity<PersonListDTO> personsWithThatName = controller.findByLastname("Marin");
        PersonListDTO list = personsWithThatName.getBody();

        Assert.assertTrue(list.getPersons().size() == 1);
        Assert.assertTrue(list.getPersons().get(0).getLastname().equals("Marin"));
        Assert.assertEquals(HttpStatus.OK, personsWithThatName.getStatusCode());
    }

    @Test
    void personWithAddressShouldBeUpdatedAndReturnedSuccess() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .address(TestData.getAddressDTO())
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));
        savedPerson.setLastname("Väliaho");

        PersonDTO personDTOFromPerson = DataMapper.getInstance().convertToDto(savedPerson);
        ResponseEntity<PersonDTO> updatedPerson =
                controller.updatePerson(savedPerson.getId(), personDTOFromPerson);

        Assert.assertEquals(HttpStatus.OK, updatedPerson.getStatusCode());
        Assert.assertTrue(updatedPerson.getBody().getLastname().equals("Väliaho"));
    }

    @Test
    void personShouldBeUpdatedAndReturnedSuccess() {

        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));
        savedPerson.setLastname("Väliaho");

        PersonDTO personDTOFromPerson = DataMapper.getInstance().convertToDto(savedPerson);
        ResponseEntity<PersonDTO> updatedPerson =
                controller.updatePerson(savedPerson.getId(), personDTOFromPerson);

        Assert.assertEquals(HttpStatus.OK, updatedPerson.getStatusCode());
        Assert.assertTrue(updatedPerson.getBody().getLastname().equals("Väliaho"));
    }

    @Test
    void personShouldNotBeUpdatedAndReturnFailed() {

        long wrongId = 10000;
        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));
        savedPerson.setLastname("Väliaho");

        PersonDTO personDTOFromPerson = DataMapper.getInstance().convertToDto(savedPerson);
        ResponseEntity<PersonDTO> updatedPerson =
                controller.updatePerson(wrongId, personDTOFromPerson);

        Assert.assertEquals(HttpStatus.NOT_FOUND, updatedPerson.getStatusCode());
        Assert.assertTrue(updatedPerson.getBody() == null);
    }

    @Test
    void getAllPersonAndReturnedSuccess() {
        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        PersonDTO person2DTO = PersonDTO.builder()
                .firstname("Valtteri")
                .lastname("Virtanen")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));
        Person savedPerson2 = repository.save(DataMapper.getInstance().convertToEntity(person2DTO));
        List<Person> found = repository.findAll();
        System.out.println("size: " + found.size());

        ResponseEntity<PersonListDTO> listOfPersons = controller.getAllPersons();
        System.out.println("size: " + listOfPersons.getBody().getPersons().size());

        Assert.assertEquals(HttpStatus.OK, listOfPersons.getStatusCode());
        Assert.assertTrue(listOfPersons.getBody().getPersons().size() > 0);

    }

    @Test
    void createPersonAndReturnedSuccess() {
        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        //Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

        ResponseEntity<PersonDTO> createdPerson = controller.createPerson(personDTO);
        System.out.println(createdPerson);
        Optional<Person> savedPerson = repository.findById(createdPerson.getBody().getId());


        Assert.assertTrue(savedPerson.isPresent());
        Assert.assertEquals(HttpStatus.CREATED, createdPerson.getStatusCode());

    }

    @Test
    void getPersonByIdAndReturnedSuccess() {
        PersonDTO personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        Person savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

        ResponseEntity<PersonDTO> foundPerson = controller.getPersonById(savedPerson.getId());
        System.out.println(foundPerson);

        Assert.assertEquals(savedPerson.getId(), foundPerson.getBody().getId());
        Assert.assertEquals(HttpStatus.OK, foundPerson.getStatusCode());

    }

    @Test
    public void exceptionThrownFromGetPersonById() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void resourceNotFoundResponseSentFromGetPersonById() {
        // ResponseEntity<>(HttpStatus.NOT_FOUND)
        long missingId = 100000;
        ResponseEntity<PersonDTO> foundPerson = controller.getPersonById(missingId);

        // TODO
        //CompanyDTO dto = null;
        //DataMapper.getInstance().convertToEntity(dto);

        System.out.println(foundPerson);

        Assert.assertEquals(HttpStatus.NOT_FOUND, foundPerson.getStatusCode());

    }

}
