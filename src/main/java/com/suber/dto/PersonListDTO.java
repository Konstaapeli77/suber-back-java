package com.suber.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
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
