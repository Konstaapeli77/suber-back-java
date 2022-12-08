package com.suber.data;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Person() {
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

}
