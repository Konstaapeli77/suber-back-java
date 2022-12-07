package com.suber.response;

import java.util.ArrayList;

import com.suber.data.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/JSON", produces="application/json")
@CrossOrigin(origins="*")
public class RestJsonResponse {

    @GetMapping("/data")
    public ArrayList<Person> get() {

        ArrayList<Person> arr = new ArrayList<>();

        Person userOne = new Person();
        userOne.setId(3);
        userOne.setFirstName("Mikko");
        userOne.setLastName("Mallikas");

        Person userTwo = new Person();
        userTwo.setId(4);
        userTwo.setFirstName("Niina");
        userTwo.setLastName("Nieminen");

        arr.add(userOne);
        arr.add(userTwo);

        return arr;
    }

    @GetMapping("/{id}/{firstname}/{lastname}")
    public ResponseEntity<Person> getData(@PathVariable("id") long id,
                                              @PathVariable("firstname") String firstName,
                                              @PathVariable("lastname") String lastName) {

        Person user = new Person();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<Person> entity = new ResponseEntity<>(user,headers,HttpStatus.CREATED);

        return entity;
    }
}
