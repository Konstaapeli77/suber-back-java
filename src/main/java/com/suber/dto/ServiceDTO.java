package com.suber.dto;

import com.suber.data.Company;
import com.suber.data.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ServiceDTO {

    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    private CompanyDTO company;

}
