package com.suber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private long id;
    private String firstname;
    private String lastname;
    private AddressDTO address;

}
