package com.suber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private long id;
    private String address;
    private String postalCode;
    private String city;


}
