package com.suber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private long id;
    private String name;
    private String businessId;
    private AddressDTO address;

}
