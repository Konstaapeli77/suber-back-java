package com.suber.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class PersonListDTO {

    @JsonValue
    private List<PersonDTO> persons;

    public PersonListDTO() {
        persons = new ArrayList<>();
    }
    public PersonListDTO(List<PersonDTO> persons) {
        this.persons = persons;
    }

}
