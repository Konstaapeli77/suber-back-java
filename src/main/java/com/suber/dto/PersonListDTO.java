package com.suber.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
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
